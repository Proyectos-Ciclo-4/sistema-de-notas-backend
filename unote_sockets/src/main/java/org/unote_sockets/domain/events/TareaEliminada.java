package org.unote_sockets.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class TareaEliminada extends DomainEvent {

    String tareaID;
    String temaID;

    public TareaEliminada(){
        super("unote.tareaEliminada");
    }

    public TareaEliminada( String tareaID, String temaID) {
        super("unote.tareaEliminada");
        this.tareaID = tareaID;
        this.temaID = temaID;
    }


    public String getTareaID() {
        return tareaID;
    }

    public String getTemaID() {
        return temaID;
    }
}
