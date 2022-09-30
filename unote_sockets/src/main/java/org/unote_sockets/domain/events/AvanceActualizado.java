package org.unote_sockets.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.unote_sockets.domain.identifiers.CursoID;
import org.unote_sockets.domain.valueobjects.Avance;

public class AvanceActualizado extends DomainEvent {
    private CursoID cursoID;
    private Avance avance;

    public AvanceActualizado(CursoID cursoID, Avance avance) {
        super("unote.avanceActualizado");
        this.cursoID = cursoID;
        this.avance = avance;
    }

    public CursoID getCursoID() {
        return cursoID;
    }

    public Avance getAvance() {
        return avance;
    }
}
