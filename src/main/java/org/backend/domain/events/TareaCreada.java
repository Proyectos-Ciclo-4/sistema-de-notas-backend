package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.valueobjects.FechaLimite;
import org.backend.domain.valueobjects.Porcentaje;
import org.backend.domain.valueobjects.Titulo;

import java.util.UUID;

public class TareaCreada extends DomainEvent {
    private String tareaID;
    private String cursoID;
    private String titulo;
    private String fechaLimite;
    private Float porcentaje;

    public TareaCreada() {
        super("unote.tareaCreada");
    }

    public TareaCreada(String titulo, String fechaLimite, Float porcentaje, String cursoID) {
        super("unote.tareaCreada");
        this.tareaID = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.fechaLimite = fechaLimite;
        this.porcentaje = porcentaje;
        this.cursoID = cursoID;
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
