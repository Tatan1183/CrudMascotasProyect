
package com.app.web.servicio;

import com.app.web.modelo.Mascota;
import com.app.web.repositorio.MascotaRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaServicioImpl implements MascotaServicio {

    private static final Logger logger = LoggerFactory.getLogger(MascotaServicioImpl.class);

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    @Override
    public List<Mascota> listarTodasLasMascotas() {
        logger.info("Listando todas las mascotas");
        return mascotaRepositorio.findAll();
    }

    @Override
    public Mascota obtenerMascotaPorId(Long id) {
        logger.info("Obteniendo mascota con ID: {}", id);
        return mascotaRepositorio.findById(id).orElse(null);
    }

    @Override
    public Mascota guardarMascota(Mascota mascota) {
        logger.info("Guardando nueva mascota: {}", mascota);
        return mascotaRepositorio.save(mascota);
    }

    @Override
    public Mascota actualizarMascota(Mascota mascota) {
        logger.info("Actualizando mascota: {}", mascota);
        return mascotaRepositorio.save(mascota);
    }

    @Override
    public void eliminarMascota(Long id) {
        logger.info("Eliminando mascota con ID: {}", id);
        mascotaRepositorio.deleteById(id);
    }
}

