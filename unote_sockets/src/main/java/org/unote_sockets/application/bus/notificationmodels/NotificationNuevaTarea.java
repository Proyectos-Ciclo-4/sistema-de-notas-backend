package org.unote_sockets.application.bus.notificationmodels;


import org.unote_sockets.models.generics.EstadoTareaGeneric;

public class NotificationNuevaTarea {
    private final String estudianteID;
    private final EstadoTareaGeneric estadoTareaGeneric;

    private final String cursoID;

    public NotificationNuevaTarea(String estudianteID, EstadoTareaGeneric estadoTareaGeneric, String cursoID) {
        this.estudianteID = estudianteID;
        this.estadoTareaGeneric = estadoTareaGeneric;
        this.cursoID = cursoID;
    }

    public String getEstudianteID() {
        return estudianteID;
    }

    public EstadoTareaGeneric getEstadoTareaGeneric() {
        return estadoTareaGeneric;
    }

    public String getCursoID() {
        return cursoID;
    }
}
