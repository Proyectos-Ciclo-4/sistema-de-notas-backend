package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class TareaCalificada extends DomainEvent {

    private String estudianteID;
    private String cursoID;
    private String tareaID;
    private Integer calificacion;
    private String retroalimentacion;

    public TareaCalificada(String type, String estudianteID, String cursoID, String tareaID, Integer calificacion, String retroalimentacion) {
        super("unote.tareaCalificada");
        this.estudianteID = estudianteID;
        this.cursoID = cursoID;
        this.tareaID = tareaID;
        this.calificacion = calificacion;
        this.retroalimentacion = retroalimentacion;
    }

    public String getEstudianteID() {
        return estudianteID;
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

    public String getRetroalimentacion() {
        return retroalimentacion;
    }
}
