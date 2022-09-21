package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.ProfesorID;

import java.util.Set;

public class CursoAgregado extends DomainEvent {
    private final ProfesorID profesorID;
    private final Set<CursoID> cursoIDs;

    public CursoAgregado(ProfesorID profesorID, Set<CursoID> cursoIDs) {
        super("cursoAgregado");
        this.profesorID = profesorID;
        this.cursoIDs = cursoIDs;
    }

    public ProfesorID getProfesorID() {
        return profesorID;
    }

    public Set<CursoID> getCursoIDs() {
        return cursoIDs;
    }
}
