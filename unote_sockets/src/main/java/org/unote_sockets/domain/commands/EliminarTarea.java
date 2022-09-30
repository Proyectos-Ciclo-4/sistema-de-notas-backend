package org.unote_sockets.domain.commands;

import co.com.sofka.domain.generic.Command;

public class EliminarTarea extends Command {

    String cursoID;
    String tareaID;
    String temaID;

    public EliminarTarea() {
    }

    public EliminarTarea(String cursoID, String tareaID, String temaID) {
        this.cursoID = cursoID;
        this.tareaID = tareaID;
        this.temaID = temaID;
    }

    public String getCursoID() {
        return cursoID;
    }

    public String getTareaID() {
        return tareaID;
    }

    public String getTemaID() {
        return temaID;
    }
}
