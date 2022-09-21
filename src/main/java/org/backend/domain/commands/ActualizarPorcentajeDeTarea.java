package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.backend.domain.identifiers.TemaID;

public class ActualizarPorcentajeDeTarea extends Command{
    private String tareaID;
    private String Porcentaje;
    private TemaID temaID;

    public ActualizarPorcentajeDeTarea() {
    }

    public ActualizarPorcentajeDeTarea(String tareaID, String porcentaje, TemaID temaID) {
        this.tareaID = tareaID;
        Porcentaje = porcentaje;
        this.temaID = temaID;
    }

    public String getTareaID() {
        return tareaID;
    }

    public String getPorcentaje() {
        return Porcentaje;
    }

    public TemaID getTemaID() {
        return temaID;
    }
}
