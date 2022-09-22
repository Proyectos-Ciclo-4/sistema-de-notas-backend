package org.backend.business.models.vistasmaterializadas;

import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;

import java.util.Set;

public class VistaEstudiante {
    private String firebaseID;
    private Float promedio;
    private Float avance;
    private Set<EstadoTareaGeneric> estadosTarea;

    public VistaEstudiante(String firebaseID, Float promedio, Float avance, Set<EstadoTareaGeneric> estadosTarea) {
        this.firebaseID = firebaseID;
        this.promedio = promedio;
        this.avance = avance;
        this.estadosTarea = estadosTarea;
    }

    public String getFirebaseID() {
        return firebaseID;
    }

    public void setFirebaseID(String firebaseID) {
        this.firebaseID = firebaseID;
    }

    public Float getPromedio() {
        return promedio;
    }

    public void setPromedio(Float promedio) {
        this.promedio = promedio;
    }

    public Float getAvance() {
        return avance;
    }

    public void setAvance(Float avance) {
        this.avance = avance;
    }

    public Set<EstadoTareaGeneric> getEstadosTarea() {
        return estadosTarea;
    }

    public void setEstadosTarea(Set<EstadoTareaGeneric> estadosTarea) {
        this.estadosTarea = estadosTarea;
    }
}
