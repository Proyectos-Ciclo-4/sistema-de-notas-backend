package org.unote_sockets.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import org.unote_sockets.domain.events.CursoCreado;
import org.unote_sockets.domain.identifiers.CursoID;
import org.unote_sockets.domain.identifiers.EstudianteID;
import org.unote_sockets.domain.identifiers.ProfesorID;
import org.unote_sockets.domain.valueobjects.Titulo;
import org.unote_sockets.models.generics.TemaGeneric;

import java.util.Set;

public class Curso extends AggregateEvent<CursoID> {

    // Propiedades
    protected Titulo titulo;

    protected Set<TemaGeneric> temas;
    protected Set<EstudianteID> estudianteID;
    protected ProfesorID profesorID;

    // Constructores
    public Curso(CursoID entityId, Titulo titulo, Set<TemaGeneric> temas, ProfesorID profesorID) {
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

    public Set<TemaGeneric> temas() {
        return temas;
    }

    public Set<EstudianteID> estudianteID() {
        return estudianteID;
    }

    public ProfesorID profesorID() {
        return profesorID;
    }

}
