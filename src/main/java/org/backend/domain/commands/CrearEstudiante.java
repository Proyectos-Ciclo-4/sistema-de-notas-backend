package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class CrearEstudiante extends Command {
    private String estudianteID;
    private String name;

    // No es necesario crear una propiedad para el Map<curso_id, inscripcion> porque al crear el estudiante,
    // esa propiedad no existe. Sólo se genera al inscribirse en un curso.

    public CrearEstudiante(String estudianteID, String name) {
        this.estudianteID = estudianteID;
        this.name = name;
    }

    public String getEstudianteID() {
        return estudianteID;
    }

    public String getName() {
        return name;
    }
}
