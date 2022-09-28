package org.backend.business.models.vistasmaterializadas.generics;

import java.awt.font.TextHitInfo;
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
        this.temaID = temaID;
        this.temaNombre = temaNombre;
        this.titulo = titulo;
        this.fechaLimite = fechaLimite;
        this.calificacion = 0;
        this.fechaEntregado = null;
        this.URLArchivo = "";
        this.estado = "sin entregar";
    }

    public EstadoTareaGeneric(String tareaID, String URLArchivo) {
        this.fechaEntregado = LocalDate.now();
        this.URLArchivo = URLArchivo;
        this.estado = "entregada";
    }

    public EstadoTareaGeneric() {
    }

    public String getTareaID() {
        return tareaID;
    }

    public void setTareaID(String tareaID) {
        this.tareaID = tareaID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTemaID() {
        return temaID;
    }

    public void setTemaID(String temaID) {
        this.temaID = temaID;
    }

    public String getTemaNombre() {
        return temaNombre;
    }

    public void setTemaNombre(String temaNombre) {
        this.temaNombre = temaNombre;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EstadoTareaGeneric actualizarTarea(String URLArchivo) {
        this.setURLArchivo(URLArchivo);
        this.setFechaEntregado(LocalDate.now());
        this.setEstado("entregado");

        return this;
    }
}
