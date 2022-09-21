package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

import java.io.File;
import java.util.Date;

public class ActualizarEstadoTarea extends Command {
    private Integer calificacion;
    private Date fechaEntregada;
    private File archivo;
    private Boolean entregado;

    public ActualizarEstadoTarea(Integer calificacion, Date fechaEntregada, File archivo, Boolean entregado) {
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
