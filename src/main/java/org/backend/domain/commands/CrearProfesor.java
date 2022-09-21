package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class CrearProfesor extends Command {

    private String ProfesorId;
    private String Nombre;

    public CrearProfesor(String profesorId, String nombre) {
        ProfesorId = profesorId;
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getProfesorId() {
        return ProfesorId;
    }

    public void setProfesorId(String profesorId) {
        ProfesorId = profesorId;
    }
}
