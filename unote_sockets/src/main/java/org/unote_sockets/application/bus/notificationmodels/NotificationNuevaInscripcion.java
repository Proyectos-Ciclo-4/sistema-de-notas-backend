package org.unote_sockets.application.bus.notificationmodels;


import org.unote_sockets.models.vistasmaterializadas.VistaEstudiante;

public class NotificationNuevaInscripcion {
    private final String profesorID;
    private final VistaEstudiante vistaEstudiante;

    private final String cursoID;

    public NotificationNuevaInscripcion(String profesorID, VistaEstudiante vistaEstudiante, String cursoID) {
        this.profesorID = profesorID;
        this.vistaEstudiante = vistaEstudiante;
        this.cursoID = cursoID;
    }

    public String getProfesorID() {
        return profesorID;
    }

    public VistaEstudiante getVistaEstudiante() {
        return vistaEstudiante;
    }

    public String getCursoID() {
        return cursoID;
    }
}
