package org.backend.business.models.vistasmaterializadas;

import java.time.LocalDate;

public class VistaTarea extends VistaMaterializada {
    private String tareaID;
    private LocalDate fechaLimite;
    private Float porcentaje;

    public VistaTarea(String tareaID, String fechaLimite, Float porcentaje) {
        this.tareaID = tareaID;
        this.fechaLimite = LocalDate.parse(fechaLimite);
        this.porcentaje = porcentaje;
    }

    public String getTareaID() {
        return tareaID;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }

}
