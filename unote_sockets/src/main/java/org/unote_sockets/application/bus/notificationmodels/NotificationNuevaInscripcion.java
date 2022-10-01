package org.unote_sockets.application.bus.notificationmodels;


import org.unote_sockets.models.vistasmaterializadas.VistaEstudiante;

public class NotificationNuevaInscripcion {
    private final String profesorID;
    private final VistaEstudiante vistaEstudiante;

    public NotificationNuevaInscripcion(String profesorID, VistaEstudiante vistaEstudiante) {
        this.profesorID = profesorID;
        this.vistaEstudiante = vistaEstudiante;
    }

    public String getProfesorID() {
        return profesorID;
    }

    public VistaEstudiante getVistaEstudiante() {
        return vistaEstudiante;
    }
}