package org.unote_sockets.application.bus.notificationmodels;


import org.unote_sockets.models.vistasmaterializadas.VistaEstudiante;

public class NotificationTareaEntregada {
    private final String profesorID;
    private final VistaEstudiante vistaEstudiante;

    public NotificationTareaEntregada(String profesorID, VistaEstudiante vistaEstudiante) {
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
