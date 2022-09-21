package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

import java.io.File;
import java.util.Date;

public class TareaActualizada extends DomainEvent {
    private String cursoID;
    private String tareaID;

    private Integer calificacion;
    private Date fechaEntrega;
    private File archivo;
    private Boolean estado;

    public TareaActualizada(String cursoID, String tareaID, Integer calificacion, Date fechaEntrega, File archivo, Boolean estado) {
        super("unote.tareaActualizada");
        this.cursoID = cursoID;
        this.tareaID = tareaID;
        this.calificacion = calificacion;
        this.fechaEntrega = fechaEntrega;
        this.archivo = archivo;
        this.estado = estado;
    }

    public String getCursoID() {
        return cursoID;
    }

    public String getTareaID() {
        return tareaID;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public File getArchivo() {
        return archivo;
    }

    public Boolean getEstado() {
        return estado;
    }
}
