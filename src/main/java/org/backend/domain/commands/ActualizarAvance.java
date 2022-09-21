package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class ActualizarAvance extends Command {
    private String cursoID;
    private float avance;

    public ActualizarAvance(String cursoID, float avance) {
        this.cursoID = cursoID;
        this.avance = avance;
    }

    public String getCursoID() {
        return cursoID;
    }

    public float getAvance() {
        return avance;
    }
}
