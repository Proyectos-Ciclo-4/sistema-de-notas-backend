package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class ActualizarPromedio extends Command {
    private String cursoID;
    private Float promedio;

    public ActualizarPromedio(String cursoID, Float promedio) {
        this.cursoID = cursoID;
        this.promedio = promedio;
    }

    public String getCursoID() {
        return cursoID;
    }

    public Float getPromedio() {
        return promedio;
    }
}
