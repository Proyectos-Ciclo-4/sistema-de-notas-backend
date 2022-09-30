package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class ActualizarPromedio extends Command {
    // TODO: Este comando no es detonado directamente por el front.
    //  El comando ActualizarEstadoTarea detona este comando para hacer
    //  el cálculo respectivo en el promedio en la inscripción.

    private String cursoID;
    private Float promedio;

    public ActualizarPromedio(String cursoID, Float promedio) {
        this.cursoID = cursoID;
        this.promedio = promedio;
    }

    public ActualizarPromedio() {
    }

    public String getCursoID() {
        return cursoID;
    }

    public Float getPromedio() {
        return promedio;
    }
}
