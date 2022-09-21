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

    // Propiedades
    protected Titulo titulo;

    protected Set<TemaID> temas;
    protected Set<EstudianteID> estudianteId;
    protected ProfesorID profesorID;

    // Constructores
    public Curso(CursoID entityId, Titulo titulo, Set<TemaID> temas, ProfesorID profesorID) {
        super(entityId);
        appendChange(new CursoCreado(titulo,profesorID,temas)).apply();
        subscribe(new CursoChange(this));
    }
    public Curso(CursoID entityId) {
        super(entityId);
    }

    // Getters sintácticos

    public Titulo titulo() {
        return titulo;
    }

    public Set<TemaID> temas() {
        return temas;
    }

    public Set<EstudianteID> estudianteId() {
        return estudianteId;
    }

    public ProfesorID profesorID() {
        return profesorID;
    }

}
