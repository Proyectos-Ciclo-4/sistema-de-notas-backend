package org.backend.business.models.vistasmaterializadas;

import java.util.Date;

public class VistaTarea {
    private String tareaID;
    private Date fechaLimite;
    private Float porcentaje;

    public VistaTarea(String tareaID, Date fechaLimite, Float porcentaje) {
        this.tareaID = tareaID;
        this.fechaLimite = fechaLimite;
        this.porcentaje = porcentaje;
    }

    public String getTareaID() {
        return tareaID;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }
}
