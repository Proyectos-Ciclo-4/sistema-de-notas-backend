package org.unote_sockets.domain;

import co.com.sofka.domain.generic.EventChange;
import org.unote_sockets.domain.entities.Inscripcion;
import org.unote_sockets.domain.events.*;
import org.unote_sockets.domain.identifiers.CursoID;
import org.unote_sockets.domain.valueobjects.Avance;
import org.unote_sockets.domain.valueobjects.EstadoTarea;
import org.unote_sockets.domain.valueobjects.Promedio;

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
