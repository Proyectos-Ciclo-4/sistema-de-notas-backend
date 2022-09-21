package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.InscripcionID;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.valueobjects.Avance;
import org.backend.domain.valueobjects.EstadoTarea;
import org.backend.domain.valueobjects.Promedio;

import java.awt.geom.FlatteningPathIterator;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MatriculadoEnCurso extends DomainEvent {

    private InscripcionID inscripcionID;
    private CursoID cursoID;
    private Promedio promedio;
    private Avance avance;

    private HashMap<TareaID, EstadoTarea> tareasCurso;

    public MatriculadoEnCurso(CursoID cursoID, Promedio promedio, Avance avance, List<TareaID> tareasID) {
        super("unote.matriculadoEnCurso");

        this.inscripcionID = InscripcionID.of( UUID.randomUUID().toString());
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

    public InscripcionID getInscripcionID() {
        return inscripcionID;
    }

    public CursoID getCursoID() {
        return cursoID;
    }

    public Promedio getPromedio() {
        return promedio;
    }

    public Avance getAvance() {
        return avance;
    }

    public HashMap<TareaID, EstadoTarea> getTareasCurso() {
        return tareasCurso;
    }
}
