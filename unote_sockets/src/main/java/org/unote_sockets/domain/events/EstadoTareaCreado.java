package org.unote_sockets.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class EstadoTareaCreado extends DomainEvent {

    private String estudianteID;
    private String tareaID;

    public EstadoTareaCreado(String estudianteID,String tareaID) {
        super("");
        this.estudianteID = estudianteID;
        this.tareaID = tareaID;

    }

    public String getEstudianteID() {
        return estudianteID;
    }

    public String getTareaID() {
        return tareaID;
    }
}
