package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

// Comando empleado para crear un Profesor en la base de datos.
public class CrearProfesor extends Command {
    /* Representación JSON:
            {
                profesorID: ID del usuario en Firebase del profesor.
                nombre: Nombre del profesor a crear.
            }
    */

    private String profesorID;
    private String nombre;

    public CrearProfesor() {
    }

    public CrearProfesor(String profesorID) {
        this.profesorID = profesorID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProfesorID() {
        return profesorID;
    }

}
