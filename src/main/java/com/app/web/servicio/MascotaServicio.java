
package com.app.web.servicio;

import com.app.web.modelo.Mascota;
import java.util.List;


public interface MascotaServicio {

    List<Mascota> listarTodasLasMascotas();

    Mascota obtenerMascotaPorId(Long id);

    Mascota guardarMascota(Mascota mascota);

    Mascota actualizarMascota(Mascota mascota);

    void eliminarMascota(Long id);
}