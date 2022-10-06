package org.backend.application.bus.notificationmodels;

import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;

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
