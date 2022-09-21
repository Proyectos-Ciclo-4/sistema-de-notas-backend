package org.backend.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.mongodb.client.model.geojson.LineString;
import org.backend.domain.entities.Tarea;
import org.backend.domain.events.TareaCreada;
import org.backend.domain.events.TemaCreado;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.identifiers.TemaID;
import org.backend.domain.valueobjects.FechaLimite;
import org.backend.domain.valueobjects.Orden;
import org.backend.domain.valueobjects.Porcentaje;
import org.backend.domain.valueobjects.Titulo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Tema extends AggregateEvent<TemaID> {

    // Propiedades

    protected Orden orden;
    protected Titulo titulo;

    protected Set<Tarea> tareas;

    // Constructores

    public Tema(TemaID temaID, Orden orden, Titulo titulo, Set<Tarea> tareas) {
        super(temaID);
        subscribe(new TemaChange(this));

        appendChange(new TemaCreado(orden, titulo, tareas)).apply();
    }

    private Tema(TemaID temaID) {
        super(temaID);
        subscribe((new TemaChange(this)));
    }

    private Tema from(TemaID temaID, List<DomainEvent> temaEvents) {
        Tema tema = new Tema(temaID);
        temaEvents.forEach(tema::applyEvent);

        return tema;
    }

    // Comportamientos

    public void crearTarea(Titulo titulo, FechaLimite fechaLimite, Porcentaje porcentaje) {
        Objects.requireNonNull(titulo);
        Objects.requireNonNull(fechaLimite);
        Objects.requireNonNull(porcentaje);

        appendChange(new TareaCreada(titulo, fechaLimite, porcentaje)).apply();
    }

    // Modificadores

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

}
