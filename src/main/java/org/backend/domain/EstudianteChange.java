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
            estudiante.nombre = new Nombre(estudianteCreado.getNombre());
            estudiante.cursos = estudianteCreado.getCursos();
        });

        apply((MatriculadoEnCurso matriculadoEnCurso) -> {
            CursoID cursoID = CursoID.of(matriculadoEnCurso.getCursoID());

            Inscripcion inscripcion = new Inscripcion(
                    InscripcionID.of(matriculadoEnCurso.getInscripcionID()),
                    cursoID,
                    new Promedio(matriculadoEnCurso.getPromedio()),
                    new Avance(matriculadoEnCurso.getAvance()),
                    matriculadoEnCurso.getTareasCurso()
            );

            estudiante.agregarCurso(cursoID, inscripcion);
        });

        apply((PromedioActualizado promedioActualizado) -> {
            Promedio promedio = new Promedio(promedioActualizado.getPromedio());
            estudiante.actualizarPromedioEnCurso(CursoID.of(promedioActualizado.getCursoID()), promedio);
        });

        apply((AvanceActualizado avanceActualizado) -> {
            Avance avance = new Avance(avanceActualizado.getAvance());
            estudiante.actualizarAvanceEnCurso(CursoID.of(avanceActualizado.getCursoID()), avance);
        });

        apply((TareaActualizada tareaActualizada) -> {
            EstadoTarea estadoTarea = new EstadoTarea(
                    tareaActualizada.getCalificacion(),
                    tareaActualizada.getFechaEntrega(),
                    tareaActualizada.getArchivo(),
                    tareaActualizada.getEstado());

            estudiante.actualizarTareaEnCurso(
                    CursoID.of(tareaActualizada.getCursoID()),
                    TareaID.of(tareaActualizada.getTareaID()),
                    estadoTarea
            );
        });
    }
}
