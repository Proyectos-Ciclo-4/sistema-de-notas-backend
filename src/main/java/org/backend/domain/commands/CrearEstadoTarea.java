package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class CrearEstadoTarea extends Command {

    private String estudianteID;
    private String tareaID;

    public CrearEstadoTarea(String estudianteID, String tareaID) {
        this.estudianteID = estudianteID;
        this.tareaID = tareaID;
    }

    public CrearEstadoTarea() {
    }

    public String getEstudianteID() {
        return estudianteID;
    }

    public String getTareaID() {
        return tareaID;
    }
}
