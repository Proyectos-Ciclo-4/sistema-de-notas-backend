package org.unote_sockets.domain;


import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.unote_sockets.domain.commands.CrearTarea;
import org.unote_sockets.domain.events.TareaCreada;
import org.unote_sockets.domain.events.TemaCreado;
import org.unote_sockets.domain.identifiers.CursoID;
import org.unote_sockets.domain.identifiers.TareaID;
import org.unote_sockets.domain.identifiers.TemaID;
import org.unote_sockets.domain.valueobjects.Descripcion;
import org.unote_sockets.domain.valueobjects.FechaLimite;
import org.unote_sockets.domain.valueobjects.Orden;
import org.unote_sockets.domain.valueobjects.Titulo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Tema extends AggregateEvent<TemaID> {

    // Propiedades

    protected Orden orden;
    protected Titulo titulo;

    protected Set<CrearTarea> tareas;

    // Constructores

    public Tema(TemaID temaID, Orden orden, Titulo titulo, Set<CrearTarea> tareas) {
        super(temaID);
        subscribe(new TemaChange(this));

        appendChange(new TemaCreado(orden, titulo, tareas)).apply();
    }

    public Tema(TemaID temaID) {
        super(temaID);
        subscribe((new TemaChange(this)));
    }

    public static Tema from(TemaID temaID, List<DomainEvent> temaEvents) {
        Tema tema = new Tema(temaID);
        temaEvents.forEach(tema::applyEvent);

        return tema;
    }

    // Comportamientos

    public void crearTarea(Titulo titulo, Descripcion descripcion, Orden orden, FechaLimite fechaLimite, Float porcentaje, CursoID cursoID) {
        Objects.requireNonNull(titulo);
        Objects.requireNonNull(descripcion);
        Objects.requireNonNull(orden);
        Objects.requireNonNull(fechaLimite);
        Objects.requireNonNull(porcentaje);
        Objects.requireNonNull(cursoID);

        appendChange(new TareaCreada(titulo.toString(), descripcion.value(), orden.value(), fechaLimite.toString(), porcentaje, cursoID.toString())).apply();
    }

    // Modificadores

    public void agregarTarea(CrearTarea tarea) {
        tareas.add(tarea);
    }

    public Optional<CrearTarea> encontrarTareaPorId(TareaID id){
        return  tareas.stream()
                .filter( tarea -> tarea.uuid().equals(id))
                .findFirst();
    }

}
