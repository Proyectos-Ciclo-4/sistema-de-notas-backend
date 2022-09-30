package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.identifiers.TemaID;
import org.backend.domain.valueobjects.Titulo;

import java.util.Set;

public class CursoCreado extends DomainEvent {
    private final Titulo titulo;
    private final ProfesorID profesorID;
    private final Set<TemaGeneric> temas;

    public CursoCreado(Titulo titulo, ProfesorID profesorID, Set<TemaGeneric> temas ) {
        super("unote.cursoCreado");

        this.titulo = titulo;
        this.profesorID = profesorID;
        this.temas = temas;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public ProfesorID getProfesorID() {
        return profesorID;
    }

    public Set<TemaGeneric> getTemas() {
        return temas;
    }
}
