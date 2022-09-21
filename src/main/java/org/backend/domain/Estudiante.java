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

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Estudiante extends AggregateEvent<EstudianteID> {
    protected Nombre nombre;

    // TODO: convertir este hashmap en un VO. Hay mucha repetición aquí, en EstudianteChagne y EstudianteCreado
    // TODO: Hacer lo mismo para hashmap de inscripción
    protected HashMap<CursoID, Inscripcion> cursos;

    // Constructores

    public Estudiante(EstudianteID entityId, Nombre nombre, HashMap<CursoID, Inscripcion> cursos) {
        super(entityId);
        subscribe(new EstudianteChange(this));
        appendChange(new EstudianteCreado(
                nombre.value(),
                cursos
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

    public void matricularEnCurso(CursoID cursoID, Promedio promedio, Avance avance, List<TareaID> tareasID){
        Objects.requireNonNull(cursoID);
        Objects.requireNonNull(promedio);
        Objects.requireNonNull(avance);
        Objects.requireNonNull(tareasID);

        // Crear evento
        appendChange(
                new MatriculadoEnCurso(cursoID.value(), promedio.value(), avance.value(), tareasID)
        ).apply();
    }

    public void actualizarPromedio(CursoID cursoID, Promedio promedio) {
        Objects.requireNonNull(cursoID);
        Objects.requireNonNull(promedio);

        // Crear evento
        appendChange(
                new PromedioActualizado(cursoID.value(), promedio.value())
        ).apply();
    }

    public void actualizarAvance(CursoID cursoID, Avance avance) {
        Objects.requireNonNull(cursoID);
        Objects.requireNonNull(avance);

        appendChange(
                new AvanceActualizado(cursoID.value(), avance.value())
        ).apply();
    }

    public void actualizarTaera(CursoID cursoID, TareaID tareaID, EstadoTarea estadoTarea) {
        Objects.requireNonNull(cursoID);
        Objects.requireNonNull(tareaID);

        appendChange(
                new TareaActualizada(
                        cursoID.value(),
                        tareaID.value(),
                        estadoTarea.Calificacion(),
                        estadoTarea.FechaEntregado(),
                        estadoTarea.Archivo(),
                        estadoTarea.Estado()
                )
        ).apply();
    }


    // Modificadores

    public void agregarCurso(CursoID newCursoID, Inscripcion newInscripcion) {
        cursos.put(newCursoID, newInscripcion);
    }

    public void actualizarPromedioEnCurso(CursoID cursoID, Promedio newPromedio) {
        cursos.get(cursoID).actualizarPromedio(newPromedio);
    }

    public void actualizarAvanceEnCurso(CursoID cursoID, Avance newAvance) {
        cursos.get(cursoID).actualizarAvance(newAvance);
    }

    public void actualizarTareaEnCurso(CursoID cursoID, TareaID tareaID, EstadoTarea estadoTareaEntrante) {
        // Recuperar el estado de la tarea original, para así contrastarlo con el nuevo estado entrante
        EstadoTarea targetTareaActual = cursos
                .get(cursoID)
                .TareasCurso()
                .get(tareaID);

        // TODO: refactor, crear función para no repetir tanto ternario
        EstadoTarea estadoTareaActualizado = new EstadoTarea(
                estadoTareaEntrante.Calificacion() == null ? targetTareaActual.Calificacion() : estadoTareaEntrante.Calificacion(),
                estadoTareaEntrante.FechaEntregado() == null ? targetTareaActual.FechaEntregado() : estadoTareaEntrante.FechaEntregado(),
                estadoTareaEntrante.Archivo() == null ? targetTareaActual.Archivo() : estadoTareaEntrante.Archivo(),
                estadoTareaEntrante.Estado()
        );

        cursos.get(cursoID).actualizarTarea(tareaID, estadoTareaActualizado);
    }

}