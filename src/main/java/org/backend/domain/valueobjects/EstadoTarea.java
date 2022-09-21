package org.backend.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

import java.io.File;
import java.util.Date;

public class EstadoTarea implements ValueObject<Object> {

    private final Integer calificacion;
    private final Date fechaEntregado;
    private final File archivo;
    private final Boolean estado;

    public EstadoTarea(Integer calificacion, Date fechaEntregado, File archivo, Boolean estado) {
        this.calificacion = calificacion;
        this.fechaEntregado = fechaEntregado;
        this.archivo = archivo;
        this.estado = estado;
    }

    @Override
    public Object value() {
        return null;
    }
}
