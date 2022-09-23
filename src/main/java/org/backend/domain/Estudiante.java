package org.backend.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.entities.Inscripcion;
import org.backend.domain.events.*;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.EstudianteID;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.valueobjects.Avance;
import org.backend.domain.valueobjects.EstadoTarea;
import org.backend.domain.valueobjects.Nombre;
import org.backend.domain.valueobjects.Promedio;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Estudiante extends AggregateEvent<EstudianteID> {

    // Propiedades

    protected Nombre nombre;

    // Esta propiedad no puede ser un VO, porque tendríamos que generar el HM cada vez que querramos cambiarlo.
    protected HashMap<CursoID, Inscripcion> inscripciones;

    // Constructores

    public Estudiante(EstudianteID entityId, Nombre nombre, HashMap<CursoID, Inscripcion> inscripciones) {
        super(entityId);
        subscribe(new EstudianteChange(this));
        appendChange(new EstudianteCreado(
                nombre,
                inscripciones
        )).apply();
    }

    private Estudiante(EstudianteID estudianteID) {
        super(estudianteID);
        subscribe(new EstudianteChange(this));
    }

    public static Estudiante from(EstudianteID estudianteID, List<DomainEvent> estudianteEvents) {
        Estudiante estudiante = new Estudiante(estudianteID);
        estudianteEvents.forEach(estudiante::applyEvent);

        return estudiante;
    }

    // Comportamientos

    public void inscribirEnCurso(CursoID cursoID, Promedio promedio, Avance avance, List<TareaID> tareasID){
        Objects.requireNonNull(cursoID);
        Objects.requireNonNull(promedio);
        Objects.requireNonNull(avance);
        Objects.requireNonNull(tareasID);

        // Crear evento
        appendChange(
                new InscritoEnCurso(cursoID, promedio, avance, tareasID)
        ).apply();
    }

    public void actualizarPromedio(CursoID cursoID, Promedio promedio) {
        Objects.requireNonNull(cursoID);
        Objects.requireNonNull(promedio);

        // Crear evento
        appendChange(
                new PromedioActualizado(cursoID, promedio)
        ).apply();
    }

    public void actualizarAvance(CursoID cursoID, Avance avance) {
        Objects.requireNonNull(cursoID);
        Objects.requireNonNull(avance);

        appendChange(
                new AvanceActualizado(cursoID, avance)
        ).apply();
    }

    public void actualizarTarea(CursoID cursoID, TareaID tareaID, EstadoTarea estadoTarea) {
        Objects.requireNonNull(cursoID);
        Objects.requireNonNull(tareaID);

        appendChange(
                new TareaActualizada(
                        cursoID,
                        tareaID,
                        estadoTarea.Calificacion(),
                        estadoTarea.FechaEntregado(),
                        estadoTarea.Archivo(),
                        estadoTarea.Estado(),
                        estadoTarea.Retroalimentacion()
                )
        ).apply();
    }

    // Modificadores

    public void agregarCursoAInscripciones(CursoID newCursoID, Inscripcion newInscripcion) {
        inscripciones.put(newCursoID, newInscripcion);
    }

    public void actualizarPromedioEnCurso(CursoID cursoID, Promedio newPromedio) {
        inscripciones.get(cursoID).actualizarPromedio(newPromedio);
    }

    public void actualizarAvanceEnCurso(CursoID cursoID, Avance newAvance) {
        inscripciones.get(cursoID).actualizarAvance(newAvance);
    }

    public void actualizarTareaEnCurso(CursoID cursoID, TareaID tareaID, EstadoTarea estadoTareaEntrante) {
        // Recuperar el estado de la tarea original, para así contrastarlo con el nuevo estado entrante
        EstadoTarea targetTareaActual = inscripciones
                .get(cursoID)
                .TareasCurso()
                .get(tareaID);

        EstadoTarea estadoTareaActualizado = new EstadoTarea(
                estadoTareaEntrante.Calificacion() == null ? targetTareaActual.Calificacion() : estadoTareaEntrante.Calificacion(),
                estadoTareaEntrante.FechaEntregado() == null ? targetTareaActual.FechaEntregado() : estadoTareaEntrante.FechaEntregado(),
                estadoTareaEntrante.Archivo() == null ? targetTareaActual.Archivo() : estadoTareaEntrante.Archivo(),
                estadoTareaEntrante.Estado(),
                estadoTareaEntrante.Retroalimentacion() == null ? targetTareaActual.Retroalimentacion() : estadoTareaEntrante.Retroalimentacion()
        );

        inscripciones.get(cursoID).actualizarTarea(tareaID, estadoTareaActualizado);
    }

}