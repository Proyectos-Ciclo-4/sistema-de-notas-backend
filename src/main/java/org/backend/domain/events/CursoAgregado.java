package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.ProfesorID;

import java.util.Set;

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
