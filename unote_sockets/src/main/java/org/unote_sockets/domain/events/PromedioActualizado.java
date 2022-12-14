package org.unote_sockets.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.unote_sockets.domain.identifiers.CursoID;
import org.unote_sockets.domain.valueobjects.Promedio;

public class PromedioActualizado extends DomainEvent {
    private CursoID cursoID;
    private Promedio promedio;

    public PromedioActualizado(CursoID cursoID, Promedio promedio) {
        super("unote.promedioActualizado");
        this.cursoID = cursoID;
        this.promedio = promedio;
    }

    public CursoID getCursoID() {
        return cursoID;
    }

    public Promedio getPromedio() {
        return promedio;
    }
}
