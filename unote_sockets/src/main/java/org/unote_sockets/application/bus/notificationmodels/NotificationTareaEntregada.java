package org.unote_sockets.application.bus.notificationmodels;


import org.unote_sockets.models.generics.EstadoTareaGeneric;
import org.unote_sockets.models.vistasmaterializadas.VistaEstudiante;

public class NotificationTareaEntregada {
    private final String profesorID;

    private final EstadoTareaGeneric estadoTareaGeneric;

    private final String cursoID;

    public NotificationTareaEntregada(String profesorID, EstadoTareaGeneric estadoTareaGeneric, String cursoID) {
        this.profesorID = profesorID;
        this.estadoTareaGeneric = estadoTareaGeneric;
        this.cursoID = cursoID;
    }

    public String getProfesorID() {
        return profesorID;
    }

    public EstadoTareaGeneric getEstadoTareaGeneric() {
        return estadoTareaGeneric;
    }

    public String getCursoID() {
        return cursoID;
    }
}
