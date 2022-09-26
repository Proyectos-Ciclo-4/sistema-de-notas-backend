package org.backend.business.models.vistasmaterializadas;

import java.time.LocalDate;

public class VistaTarea {
    private String _id;

    private String cursoID;

    private String temaID;

    private String titulo;
    
    private String descripcion;

    private Integer orden;
    private LocalDate fechaLimite;
    private Float porcentaje;

    public VistaTarea(String _id, String cursoID, String temaID, String titulo, String descripcion, Integer orden, String fechaLimite, Float porcentaje) {
        this._id = _id;
        this.cursoID = cursoID;
        this.temaID = temaID;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.orden = orden;
        this.fechaLimite = LocalDate.parse(fechaLimite);
        this.porcentaje = porcentaje;
    }

    public VistaTarea() {
    }

    public String getCursoID() {
        return cursoID;
    }

    public String getTemaID() {
        return temaID;
    }

    public String get_id() {
        return _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }

}
