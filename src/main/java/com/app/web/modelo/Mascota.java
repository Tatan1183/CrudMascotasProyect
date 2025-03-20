
package com.app.web.modelo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @NotEmpty(message = "La raza no puede estar vacía")
    @Size(min = 2, max = 50, message = "La raza debe tener entre 2 y 50 caracteres")
    @Column(name = "raza", nullable = false, length = 50)
    private String raza;

    @Column(name = "edad", nullable = false, length = 2)
    private int edad;

    @NotEmpty(message = "El dueño no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre del dueño debe tener entre 2 y 50 caracteres")
    @Column(name = "dueño", nullable = false, length = 50)
    private String dueño;

    // Constructor sin argumentos
    public Mascota() {
    }

    // Constructor con todos los argumentos
    public Mascota(Long id, String nombre, String raza, int edad, String dueño) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.dueño = dueño;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }

    public String getDueño() {
        return dueño;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    // toString()
    @Override
    public String toString() {
        return "Mascota{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", edad=" + edad +
                ", dueño='" + dueño + '\'' +
                '}';
    }
}

