package org.unote_sockets.domain;

import co.com.sofka.domain.generic.EventChange;
import org.backend.domain.entities.Inscripcion;
import org.backend.domain.events.*;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.valueobjects.Avance;
import org.backend.domain.valueobjects.EstadoTarea;
import org.backend.domain.valueobjects.Promedio;

public class EstudianteChange extends EventChange {

    public EstudianteChange(Estudiante estudiante) {
        apply((EstudianteCreado estudianteCreado) -> {
            estudiante.nombre = estudianteCreado.getNombre();
            estudiante.inscripciones = estudianteCreado.getCursos();
        });

        apply((InscritoEnCurso inscritoEnCurso) -> {
            CursoID cursoID = inscritoEnCurso.getCursoID();

            Inscripcion inscripcion = new Inscripcion(
                    inscritoEnCurso.getInscripcionID(),
                    cursoID,
                    inscritoEnCurso.getPromedio(),
                    inscritoEnCurso.getAvance(),
                    inscritoEnCurso.getTareasCurso()
            );

            estudiante.agregarCursoAInscripciones(cursoID, inscripcion);
        });

        apply((PromedioActualizado promedioActualizado) -> {
            Promedio promedio = promedioActualizado.getPromedio();
            estudiante.actualizarPromedioEnCurso(promedioActualizado.getCursoID(), promedio);
        });

        apply((AvanceActualizado avanceActualizado) -> {
            Avance avance = avanceActualizado.getAvance();
            estudiante.actualizarAvanceEnCurso(avanceActualizado.getCursoID(), avance);
        });

        apply((TareaActualizada tareaActualizada) -> {
            EstadoTarea estadoTarea = new EstadoTarea(
                    tareaActualizada.getCalificacion(),
                    tareaActualizada.getFechaEntrega(),
                    tareaActualizada.getArchivo(),
                    tareaActualizada.getEstado(),
                    tareaActualizada.getRetroalimentacion()
            );

            estudiante.actualizarTareaEnCurso(
                    tareaActualizada.getCursoID(),
                    tareaActualizada.getTareaID(),
                    estadoTarea
            );
        });
    }
}
