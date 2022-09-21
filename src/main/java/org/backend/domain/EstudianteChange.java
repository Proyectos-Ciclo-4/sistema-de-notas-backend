package org.backend.domain;

import co.com.sofka.domain.generic.EventChange;
import org.backend.domain.entities.Inscripcion;
import org.backend.domain.events.*;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.InscripcionID;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.valueobjects.Avance;
import org.backend.domain.valueobjects.EstadoTarea;
import org.backend.domain.valueobjects.Nombre;
import org.backend.domain.valueobjects.Promedio;

public class EstudianteChange extends EventChange {

    public EstudianteChange(Estudiante estudiante) {
        apply((EstudianteCreado estudianteCreado) -> {
            estudiante.nombre = estudianteCreado.getNombre();
            estudiante.cursos = estudianteCreado.getCursos();
        });

        apply((MatriculadoEnCurso matriculadoEnCurso) -> {
            CursoID cursoID = matriculadoEnCurso.getCursoID();

            Inscripcion inscripcion = new Inscripcion(
                    matriculadoEnCurso.getInscripcionID(),
                    cursoID,
                    matriculadoEnCurso.getPromedio(),
                    matriculadoEnCurso.getAvance(),
                    matriculadoEnCurso.getTareasCurso()
            );

            estudiante.agregarCurso(cursoID, inscripcion);
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
                    tareaActualizada.getEstado());

            estudiante.actualizarTareaEnCurso(
                    tareaActualizada.getCursoID(),
                    tareaActualizada.getTareaID(),
                    estadoTarea
            );
        });
    }
}
