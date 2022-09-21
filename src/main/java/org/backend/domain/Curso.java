package org.backend.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import org.backend.domain.events.CursoCreado;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.EstudianteID;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.identifiers.TemaID;
import org.backend.domain.valueobjects.Titulo;

import java.util.Set;

public class Curso extends AggregateEvent<CursoID> {

    protected Titulo titulo;

    public Curso(CursoID entityId, Titulo titulo, Set<TemaID> tema_id, ProfesorID profesorID) {
        super(entityId);
        appendChange(new CursoCreado(titulo,profesorID,tema_id)).apply();
        subscribe(new CursoChange(this));
    }

    protected Set<TemaID> tema_id;
    protected Set<EstudianteID> estudianteId;
    protected ProfesorID profesorID;

    public Titulo getTitulo() {
        return titulo;
    }

    public Set<TemaID> tema_id() {
        return tema_id;
    }

    public Set<EstudianteID> estudianteId() {
        return estudianteId;
    }

    public ProfesorID profesorID() {
        return profesorID;
    }

    public Curso(CursoID entityId) {
        super(entityId);
    }
}
