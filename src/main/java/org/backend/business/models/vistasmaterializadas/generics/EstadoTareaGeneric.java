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
    private String archivoURL;
    private String estado;

    private Integer orden;

    public Integer getorden() {
        return orden;
    }

    public void setNumero(Integer numero) {
        this.orden = numero;
    }
// Constructor para crear objeto EstadoTareaGeneric por primera vez


    public EstadoTareaGeneric(String tareaID, String titulo, String temaID, String temaNombre, LocalDate fechaLimite, Integer orden) {
        this.tareaID = tareaID;
        this.titulo = titulo;
        this.temaID = temaID;
        this.temaNombre = temaNombre;
        this.calificacion = 0;
        this.fechaLimite = fechaLimite;
        this.fechaEntregado = null;
        this.archivoURL = "";
        this.estado = "sin entregar";
        this.orden = orden;
    }

    public EstadoTareaGeneric(String tareaID, String archivoURL) {
        this.fechaEntregado = LocalDate.now();
        this.archivoURL = archivoURL;
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

    public String getArchivoURL() {
        return archivoURL;
    }

    public void setArchivoURL(String archivoURL) {
        this.archivoURL = archivoURL;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EstadoTareaGeneric actualizarTarea(String archivoURL) {
        this.setArchivoURL(archivoURL);
        this.setFechaEntregado(LocalDate.now());
        this.setEstado("entregado");

        return this;
    }

}
