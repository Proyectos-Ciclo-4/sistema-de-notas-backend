package org.unote_sockets.domain.commands;

import co.com.sofka.domain.generic.Command;

// Comando empleado para inscribir un Estudiante a un curso.
public class CrearInscripcion extends Command {
        /* Representación JSON:
            {
                estudianteID: ID del usuario en Firebase del estudiante.
                cursoID: ID del curso al cual se inscribió el estudiante.
                nombreCurso: Nombre del curso al cual se inscribió el estudiante.
            }
        */

    private String estudianteID;
    private String cursoID;

    private String nombreCurso;

    // No es necesario crear la propiedad Map<tarea_id, estado_tarea> tareas_curso
    // porque las tareas se asignan en el backend y luego se regresan.

    // No es necesario crear la propiedad promedio ni avance, porque por defecto, al crear
    // la inscripción, serán iguales a 0.


    public CrearInscripcion(String estudianteID, String cursoID, String nombreCurso) {
        this.estudianteID = estudianteID;
        this.cursoID = cursoID;
        this.nombreCurso = nombreCurso;
    }

    public CrearInscripcion() {
    }

    public String getEstudianteID() {
        return estudianteID;
    }

    public String getCursoID() {
        return cursoID;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }
}
