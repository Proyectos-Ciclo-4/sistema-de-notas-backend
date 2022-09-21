package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.entities.Tarea;
import org.backend.domain.identifiers.TemaID;
import org.backend.domain.valueobjects.Orden;
import org.backend.domain.valueobjects.Titulo;

import java.util.Set;

public class TemaCreado extends DomainEvent {
    private Orden orden;
    private Titulo titulo;
    private Set<Tarea> tareas;

    public TemaCreado() {
        super("unote.temaCreado");
    }

    public TemaCreado(Orden orden, Titulo titulo, Set<Tarea> tareas) {
        super("unote.temaCreado");
        this.orden = orden;
        this.titulo = titulo;
        this.tareas = tareas;
    }

    public Orden getOrden() {
        return orden;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public Set<Tarea> getTareas() {
        return tareas;
    }
}
