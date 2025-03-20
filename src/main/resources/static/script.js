const listaMascotas = document.getElementById('lista-mascotas');
const formAgregarMascota = document.getElementById('form-agregar-mascota');

const mostrarMascotas = (mascotas) => {
    listaMascotas.innerHTML = '';
    mascotas.forEach(mascota => {
        listaMascotas.innerHTML += `
            <tr>
                <td>${mascota.nombre}</td>
                <td>${mascota.raza}</td>
                <td>${mascota.edad}</td>
                <td>${mascota.dueño}</td>
                <td class="text-center">
                    <button class="btn btn-sm btn-primary" onclick="cargarMascotaEnFormulario(${mascota.id})">Editar</button>
                    <button class="btn btn-sm btn-danger" onclick="eliminarMascota(${mascota.id})">Eliminar</button>
                </td>
            </tr>
        `;
    });
}

const obtenerMascotas = () => {
    fetch('/api/mascotas')
            .then(response => response.json())
            .then(data => {
                console.log('Lista de mascotas:', data);
                mostrarMascotas(data);
            })
            .catch(error => {
                console.error('Error:', error);
                showAlert('Error al obtener la lista de mascotas.', 'danger');
            });
}

const cargarMascotaEnFormulario = (id) => {
    fetch(`/api/mascotas/${id}`)
            .then(response => response.json())
            .then(mascota => {
                formAgregarMascota.nombre.value = mascota.nombre;
                formAgregarMascota.raza.value = mascota.raza;
                formAgregarMascota.edad.value = mascota.edad;
                formAgregarMascota.dueño.value = mascota.dueño;
                formAgregarMascota.id.value = mascota.id;
            })
            .catch(error => {
                console.error('Error:', error);
                showAlert('Error al cargar la mascota.', 'danger');
            });
}

const eliminarMascota = (id) => {
    const url = `/api/mascotas/${id}`;
    console.log('Enviando petición DELETE a:', url);

    fetch(url, {
        method: 'DELETE'
    })
            .then(response => {
                if (response.ok) {
                    console.log('Mascota eliminada correctamente.');
                    obtenerMascotas();
                    showAlert('Mascota eliminada con éxito!', 'success');
                } else {
                    console.error('Error al eliminar la mascota:', response.status, response.statusText);
                    showAlert('Error al eliminar la mascota.', 'danger');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                showAlert('Error al eliminar la mascota.', 'danger');
            });
}

const limpiarFormulario = () => {
    formAgregarMascota.nombre.value = '';
    formAgregarMascota.raza.value = '';
    formAgregarMascota.edad.value = '';
    formAgregarMascota.dueño.value = '';
    formAgregarMascota.id.value = '';
}

const showAlert = (message, type) => {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    `;
    document.querySelector('.container').insertBefore(alertDiv, document.querySelector('.table-responsive'));
    setTimeout(() => {
        alertDiv.remove();
    }, 3000);
};

formAgregarMascota.addEventListener('submit', async (event) => {
    event.preventDefault();

    const nuevaMascota = {
        nombre: formAgregarMascota.nombre.value,
        raza: formAgregarMascota.raza.value,
        edad: parseInt(formAgregarMascota.edad.value),
        dueño: formAgregarMascota.dueño.value
    };

    const method = formAgregarMascota.id.value ? 'PUT' : 'POST';
    const url = formAgregarMascota.id.value ? `/api/mascotas/${formAgregarMascota.id.value}` : '/api/mascotas';

    try {
        const response = await fetch(url, {
            method: method,
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(nuevaMascota)
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        console.log('Mascota agregada/actualizada:', data);
        obtenerMascotas();
        limpiarFormulario();
        showAlert('Mascota guardada con éxito!', 'success');
    } catch (error) {
        console.error('Error:', error);
        showAlert('Error al guardar la mascota.', 'danger');
    }
});

document.addEventListener('DOMContentLoaded', obtenerMascotas);