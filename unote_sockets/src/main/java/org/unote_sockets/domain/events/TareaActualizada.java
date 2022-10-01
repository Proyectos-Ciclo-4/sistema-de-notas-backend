package org.unote_sockets.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.unote_sockets.domain.identifiers.CursoID;
import org.unote_sockets.domain.identifiers.TareaID;

import java.io.File;
import java.util.Date;

public class TareaActualizada extends DomainEvent {
    private CursoID cursoID;
    private TareaID tareaID;

    private Integer calificacion;
    private Date fechaEntrega;
    private File archivo;
    private Boolean estado;

    private String retroalimentacion;

    public TareaActualizada(CursoID cursoID, TareaID tareaID, Integer calificacion, Date fechaEntrega, File archivo, Boolean estado, String retroalimentacion) {
        super("unote.tareaActualizada");
        this.cursoID = cursoID;
        this.tareaID = tareaID;
        this.calificacion = calificacion;
        this.fechaEntrega = fechaEntrega;
        this.archivo = archivo;
        this.estado = estado;
        this.retroalimentacion = retroalimentacion;
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

    public String getRetroalimentacion() {
        return retroalimentacion;
    }
}
