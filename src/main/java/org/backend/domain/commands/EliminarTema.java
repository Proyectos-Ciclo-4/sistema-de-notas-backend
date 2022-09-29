package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class EliminarTema extends Command {
    String cursoID;
    String temaID;

    public EliminarTema(String cursoID, String temaID) {
        this.cursoID = cursoID;
        this.temaID = temaID;
    }

    public EliminarTema() {
    }

    public String getCursoID() {
        return cursoID;
    }

    public String getTemaID() {
        return temaID;
    }
}
