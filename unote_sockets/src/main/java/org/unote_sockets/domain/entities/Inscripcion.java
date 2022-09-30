package org.unote_sockets.domain.entities;

import co.com.sofka.domain.generic.Entity;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.InscripcionID;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.valueobjects.Avance;
import org.backend.domain.valueobjects.EstadoTarea;
import org.backend.domain.valueobjects.Promedio;

import java.util.HashMap;
import java.util.Objects;

public class Inscripcion extends Entity<InscripcionID> {
    private CursoID cursoID;
    private Promedio promedio;
    private Avance avance;

    private HashMap<TareaID, EstadoTarea> tareasCurso;

    public Inscripcion(InscripcionID entityId, CursoID cursoID, Promedio promedio, Avance avance, HashMap<TareaID, EstadoTarea> tareasCurso) {
        super(entityId);
        this.cursoID = cursoID;
        this.promedio = promedio;
        this.avance = avance;
        this.tareasCurso = tareasCurso;
    }

    // Getters sintácticos
    public CursoID CursoID() {
        return cursoID;
    }

    public Promedio Promedio() {
        return promedio;
    }

    public Avance Avance() {
        return avance;
    }

    public HashMap<TareaID, EstadoTarea> TareasCurso() {
        return tareasCurso;
    }

    // Comportamientos

    public void actualizarPromedio(Promedio newPromedio) {
        this.promedio = Objects.requireNonNull(newPromedio);
    }

    public void actualizarAvance(Avance newAvance) {
        this.avance = Objects.requireNonNull(newAvance);
    }

    public void actualizarTarea(TareaID targetTareaID, EstadoTarea newEstadoTarea) {
        if (tareasCurso.containsKey(targetTareaID)) {
            tareasCurso.put(targetTareaID, newEstadoTarea);
        } else {
            throw new IllegalArgumentException("La tarea solicitada no existe en la inscripción");
        }
    }
}
