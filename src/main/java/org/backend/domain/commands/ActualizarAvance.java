package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class ActualizarAvance extends Command {
    // TODO: Este comando no es detonado directamente por el front.
    //  El comando ActualizarEstadoTarea detona este comando para hacer
    //  el cálculo respectivo en el avance del curso.

    private String cursoID;
    private float avance;

    public ActualizarAvance(String cursoID, float avance) {
        this.cursoID = cursoID;
        this.avance = avance;
    }

    public ActualizarAvance() {
    }

    public String getCursoID() {
        return cursoID;
    }

    public float getAvance() {
        return avance;
    }
}
