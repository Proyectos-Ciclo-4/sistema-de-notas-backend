package org.backend.business.models.vistasmaterializadas;

import java.time.LocalDate;

public class VistaTarea {
    private String _id;
    private LocalDate fechaLimite;
    private Float porcentaje;

    public VistaTarea(String _id, String fechaLimite, Float porcentaje) {
        this._id = _id;
        this.fechaLimite = LocalDate.parse(fechaLimite);
        this.porcentaje = porcentaje;
    }

    public String get_id() {
        return _id;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }

}
