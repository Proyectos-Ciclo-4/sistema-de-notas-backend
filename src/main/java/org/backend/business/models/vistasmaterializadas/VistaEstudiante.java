package org.backend.business.models.vistasmaterializadas;

import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;

import java.util.HashSet;
import java.util.Set;

public class VistaEstudiante {
    private String _id;
    private Float promedio;
    private Float avance;
    private Set<EstadoTareaGeneric> estadosTarea;

    public VistaEstudiante(String _id, Float promedio, Float avance, Set<EstadoTareaGeneric> estadosTarea) {
        this._id = _id;
        this.promedio = promedio;
        this.avance = avance;
        this.estadosTarea = estadosTarea;
    }
    public VistaEstudiante(String _id) {
        this._id = _id;
        this.promedio = Float.valueOf(0);
        this.avance = Float.valueOf(0);
        this.estadosTarea = new HashSet<>();
    }

    public String get_id() {
        return _id;
    }

    public void setFirebaseID(String _id) {
        this._id = _id;
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
