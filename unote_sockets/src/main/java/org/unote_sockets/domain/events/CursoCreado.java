package org.unote_sockets.domain.events;


import co.com.sofka.domain.generic.DomainEvent;
import org.unote_sockets.domain.identifiers.ProfesorID;
import org.unote_sockets.domain.valueobjects.Titulo;
import org.unote_sockets.models.generics.TemaGeneric;

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
