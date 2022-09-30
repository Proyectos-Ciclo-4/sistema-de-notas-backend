package org.unote_sockets.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

import java.io.File;
import java.util.Date;

public class EstadoTarea implements ValueObject<Object> {

    private final Integer calificacion;
    private final Date fechaEntregado;
    private final File archivo;
    private final Boolean estado;

    private final String retroalimentacion;

    public EstadoTarea(Integer calificacion, Boolean estado) {
        this.calificacion = calificacion;
        this.fechaEntregado = null;
        this.archivo = null;
        this.estado = estado;
        this.retroalimentacion = null;
    }

    public EstadoTarea(Integer calificacion, Date fechaEntregado, File archivo, Boolean estado, String retroalimentacion) {
        this.calificacion = calificacion;
        this.fechaEntregado = fechaEntregado;
        this.archivo = archivo;
        this.estado = estado;
        this.retroalimentacion = retroalimentacion;
    }

    @Override
    public Object value() {
        return null;
    }

    public Integer Calificacion() {
        return calificacion;
    }

    public Date FechaEntregado() {
        return fechaEntregado;
    }

    public File Archivo() {
        return archivo;
    }

    public Boolean Estado() {
        return estado;
    }

    public String Retroalimentacion() {
        return retroalimentacion;
    }
}
