package org.unote_sockets.domain.events;


import co.com.sofka.domain.generic.DomainEvent;
import org.unote_sockets.domain.identifiers.CursoID;

public class CursoAgregado extends DomainEvent {
    private final CursoID cursoID;

    public CursoAgregado(CursoID cursoID) {
        super("unote.cursoAgregado");
        this.cursoID = cursoID;
    }

    public CursoID getCursoID() {
        return cursoID;
    }
}
