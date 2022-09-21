package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class CrearInscripcion extends Command {
    private String inscripcionID;
    private String cursoID;

    // No es necesario crear la propiedad Map<tarea_id, estado_tarea> tareas_curso
    // porque las tareas se asignan en el backend y luego se regresan.

    // No es necesario crear la propiedad promedio ni avance, porque por defecto, al crear
    // la inscripción, serán iguales a 0.


    public CrearInscripcion(String inscripcionID, String cursoID) {
        this.inscripcionID = inscripcionID;
        this.cursoID = cursoID;
    }

    public String getInscripcionID() {
        return inscripcionID;
    }

    public String getCursoID() {
        return cursoID;
    }
}
