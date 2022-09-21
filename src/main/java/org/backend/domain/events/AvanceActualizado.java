package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.valueobjects.Avance;

public class AvanceActualizado extends DomainEvent {
    private CursoID cursoID;
    private Avance avance;

    public AvanceActualizado(CursoID cursoID, Avance avance) {
        super("domain.avanceActualizado");
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
