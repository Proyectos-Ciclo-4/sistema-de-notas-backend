package org.backend.business.models.vistasmaterializadas.generics;

import java.time.LocalDate;

public class EstadoTareaGeneric {
    private String tareaID;
    private Integer calificacion;
    private LocalDate fechaEntregado;
    private String URLArchivo;
    private Boolean estado;

    // Constructor para crear objeto EstadoTareaGeneric por primera vez
    public EstadoTareaGeneric(String tareaID) {
        this.tareaID = tareaID;
        this.calificacion = 0;
        this.fechaEntregado = null;
        this.URLArchivo = null;
        this.estado = false;
    }

    public EstadoTareaGeneric(String tareaID, String URLArchivo) {
        this.tareaID = tareaID;
        this.fechaEntregado = LocalDate.now();
        this.URLArchivo = URLArchivo;
        this.estado = true;
    }



    public String getTareaID() {
        return tareaID;
    }

    public void setTareaID(String tareaID) {
        this.tareaID = tareaID;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFechaEntregado() {
        return fechaEntregado;
    }

    public void setFechaEntregado(LocalDate fechaEntregado) {
        this.fechaEntregado = fechaEntregado;
    }

    public String getURLArchivo() {
        return URLArchivo;
    }

    public void setURLArchivo(String URLArchivo) {
        this.URLArchivo = URLArchivo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
