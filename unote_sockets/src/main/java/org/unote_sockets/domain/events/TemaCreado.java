package org.unote_sockets.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.unote_sockets.domain.commands.CrearTarea;
import org.unote_sockets.domain.valueobjects.Orden;
import org.unote_sockets.domain.valueobjects.Titulo;

import java.util.Set;

public class TemaCreado extends DomainEvent {
    private Orden orden;
    private Titulo titulo;
    private Set<CrearTarea> tareas;

    public TemaCreado() {
        super("unote.temaCreado");
    }

    public TemaCreado(Orden orden, Titulo titulo, Set<CrearTarea> tareas) {
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

    public Set<CrearTarea> getTareas() {
        return tareas;
    }
}
