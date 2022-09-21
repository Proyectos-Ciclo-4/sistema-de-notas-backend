package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.TareaID;

import java.io.File;
import java.util.Date;

public class TareaActualizada extends DomainEvent {
    private CursoID cursoID;
    private TareaID tareaID;

    private Integer calificacion;
    private Date fechaEntrega;
    private File archivo;
    private Boolean estado;

    public TareaActualizada(CursoID cursoID, TareaID tareaID, Integer calificacion, Date fechaEntrega, File archivo, Boolean estado) {
        super("unote.tareaActualizada");
        this.cursoID = cursoID;
        this.tareaID = tareaID;
        this.calificacion = calificacion;
        this.fechaEntrega = fechaEntrega;
        this.archivo = archivo;
        this.estado = estado;
    }

    public CursoID getCursoID() {
        return cursoID;
    }

    public TareaID getTareaID() {
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
