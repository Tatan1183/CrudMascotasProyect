package com.app.web.controlador;

import com.app.web.modelo.Mascota;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.app.web.servicio.MascotaServicio;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaRestController {

    @Autowired
    private MascotaServicio servicio;

    @GetMapping
    public List<Mascota> listarMascotas() {
        return servicio.listarTodasLasMascotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable Long id) {
        Mascota mascota = servicio.obtenerMascotaPorId(id);
        if (mascota != null) {
            return new ResponseEntity<>(mascota, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Mascota> crearMascota(@Valid @RequestBody Mascota mascota) {
        Mascota nuevaMascota = servicio.guardarMascota(mascota);
        return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Long id, @Valid @RequestBody Mascota mascota) {
        Mascota mascotaExistente = servicio.obtenerMascotaPorId(id);
        if (mascotaExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        mascotaExistente.setNombre(mascota.getNombre());
        mascotaExistente.setRaza(mascota.getRaza());
        mascotaExistente.setEdad(mascota.getEdad());
        mascotaExistente.setDueño(mascota.getDueño());
        Mascota mascotaActualizada = servicio.actualizarMascota(mascotaExistente);
        return new ResponseEntity<>(mascotaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        Mascota mascotaExistente = servicio.obtenerMascotaPorId(id);
        if (mascotaExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        servicio.eliminarMascota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
