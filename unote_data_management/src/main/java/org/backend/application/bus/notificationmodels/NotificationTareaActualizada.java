package org.backend.application.bus.notificationmodels;

import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;

public class NotificationTareaActualizada {
    private final String estudianteID;
    private final EstadoTareaGeneric estadoTareaGeneric;

    private final String cursoID;

    public NotificationTareaActualizada(String estudianteID, EstadoTareaGeneric estadoTareaGeneric, String cursoID) {
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
