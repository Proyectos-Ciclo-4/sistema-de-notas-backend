package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

import java.io.File;
import java.util.Date;

public class EstadoTareaActualizado extends DomainEvent {
    private Integer calificacion;
    private Date fechaEntregada;
    private File archivo;
    private Boolean entregado;

    public EstadoTareaActualizado(Integer calificacion,Date fechaEntregada,File archivo,Boolean entregado) {
        super("unote.estadoTareaActualizado");
        this.calificacion = calificacion;
        this.fechaEntregada = fechaEntregada;
        this.archivo = archivo;
        this.entregado = entregado;

    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public Date getFechaEntregada() {
        return fechaEntregada;
    }

    public File getArchivo() {
        return archivo;
    }

    public Boolean getEntregado() {
        return entregado;
    }
}
