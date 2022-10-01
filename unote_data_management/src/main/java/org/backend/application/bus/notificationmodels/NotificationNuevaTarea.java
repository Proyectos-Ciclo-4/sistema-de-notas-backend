package org.backend.application.bus.notificationmodels;

import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;

public class NotificationNuevaTarea {
    private final String estudianteID;
    private final EstadoTareaGeneric estadoTareaGeneric;

    public NotificationNuevaTarea(String estudianteID, EstadoTareaGeneric estadoTareaGeneric) {
        this.estudianteID = estudianteID;
        this.estadoTareaGeneric = estadoTareaGeneric;
    }

    public String getEstudianteID() {
        return estudianteID;
    }

    public EstadoTareaGeneric getEstadoTareaGeneric() {
        return estadoTareaGeneric;
    }
}
