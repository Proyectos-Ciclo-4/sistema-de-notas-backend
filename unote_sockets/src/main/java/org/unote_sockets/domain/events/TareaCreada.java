package org.unote_sockets.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

import java.util.UUID;

public class TareaCreada extends DomainEvent {
    private String tareaID;
    private String cursoID;
    private String titulo;

    private String descripcion;

    private Integer orden;
    private String fechaLimite;
    private Float porcentaje;

    public TareaCreada() {
        super("unote.tareaCreada");
    }

    public TareaCreada(String titulo, String descripcion, Integer orden, String fechaLimite, Float porcentaje, String cursoID) {
        super("unote.tareaCreada");
        this.tareaID = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.orden = orden;
        this.fechaLimite = fechaLimite;
        this.porcentaje = porcentaje;
        this.cursoID = cursoID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getOrden() {
        return orden;
    }

    public String getTareaID() {
        return tareaID;
    }

    public String getCursoID() {
        return cursoID;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }
}
