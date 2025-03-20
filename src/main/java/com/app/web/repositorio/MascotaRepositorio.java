
package com.app.web.repositorio;

import com.app.web.modelo.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MascotaRepositorio extends JpaRepository<Mascota, Long> {
}
