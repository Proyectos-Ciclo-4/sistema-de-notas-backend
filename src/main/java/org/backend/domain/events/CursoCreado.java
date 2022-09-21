package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.identifiers.TemaID;
import org.backend.domain.valueobjects.Titulo;

import java.util.Set;

public class CursoCreado extends DomainEvent {
    private final Titulo titulo;
    private final ProfesorID profesorID;
    private final Set<TemaID> tema_id;

    public CursoCreado(Titulo titulo, ProfesorID profesorID, Set<TemaID> tema_id ) {
        super("unote.cursocreado");

        this.titulo = titulo;
        this.profesorID = profesorID;
        this.tema_id = tema_id;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public ProfesorID getProfesorID() {
        return profesorID;
    }

    public Set<TemaID> getTema_id() {
        return tema_id;
    }
}
