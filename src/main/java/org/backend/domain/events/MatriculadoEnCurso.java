package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.valueobjects.EstadoTarea;

import java.awt.geom.FlatteningPathIterator;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MatriculadoEnCurso extends DomainEvent {

    private String inscripcionID;
    private String cursoID;
    private Float promedio;
    private Float avance;

    private HashMap<TareaID, EstadoTarea> tareasCurso;

    public MatriculadoEnCurso(String cursoID, Float promedio, Float avance, List<TareaID> tareasID) {
        super("unote.MatriculadoEnCurso");

        this.inscripcionID = UUID.randomUUID().toString();
        this.cursoID = cursoID;
        this.promedio = promedio;
        this.avance = avance;
        this.tareasCurso = new HashMap<>();

        for (TareaID tareaID : tareasID) {
            tareasCurso.put(tareaID, new EstadoTarea(
                    0,
                    null,
                    null,
                    false
            ));
        }
    }

    public String getInscripcionID() {
        return inscripcionID;
    }

    public String getCursoID() {
        return cursoID;
    }

    public Float getPromedio() {
        return promedio;
    }

    public Float getAvance() {
        return avance;
    }

    public HashMap<TareaID, EstadoTarea> getTareasCurso() {
        return tareasCurso;
    }
}
