package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class EliminarTarea extends Command {

    String estudianteID;
    String cursoID;
    String tareaID;

    public EliminarTarea() {
    }

    public EliminarTarea(String estudianteID, String cursoID, String tareaID) {
        this.estudianteID = estudianteID;
        this.cursoID = cursoID;
        this.tareaID = tareaID;
    }

    public String getEstudianteID() {
        return estudianteID;
    }

    public String getCursoID() {
        return cursoID;
    }

    public String getTareaID() {
        return tareaID;
    }
}
