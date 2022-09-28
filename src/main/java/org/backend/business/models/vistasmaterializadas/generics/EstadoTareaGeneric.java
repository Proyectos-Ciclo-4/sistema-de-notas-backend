package org.backend.business.models.vistasmaterializadas.generics;

import java.time.LocalDate;

public class EstadoTareaGeneric {
    private String tareaID;
    private String titulo;

    private String temaID;

    private String temaNombre;

    private Integer calificacion;

    private LocalDate fechaLimite;
    private LocalDate fechaEntregado;
    private String URLArchivo;
    private String estado;

    // Constructor para crear objeto EstadoTareaGeneric por primera vez


    public EstadoTareaGeneric(String tareaID, String titulo, String temaID, String temaNombre, LocalDate fechaLimite) {
        this.tareaID = tareaID;
        this.titulo = titulo;
        this.temaID = temaID;
        this.temaNombre = temaNombre;
        this.calificacion = 0;
        this.fechaLimite = fechaLimite;
        this.fechaEntregado = null;
        this.URLArchivo = null;
        this.estado = "sin entregar";
    }

    public EstadoTareaGeneric(String tareaID, String URLArchivo) {
        this.tareaID = tareaID;
        this.fechaEntregado = LocalDate.now();
        this.URLArchivo = URLArchivo;
        this.estado = "entregada";
    }

    public EstadoTareaGeneric() {
    }

    public String getTareaID() {
        return tareaID;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTemaID() {
        return temaID;
    }

    public String getTemaNombre() {
        return temaNombre;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public LocalDate getFechaEntregado() {
        return fechaEntregado;
    }

    public String getURLArchivo() {
        return URLArchivo;
    }

    public String getEstado() {
        return estado;
    }
}
