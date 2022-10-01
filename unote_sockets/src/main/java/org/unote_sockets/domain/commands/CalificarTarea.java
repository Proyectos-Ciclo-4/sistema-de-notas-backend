package org.unote_sockets.domain.commands;

import co.com.sofka.domain.generic.Command;

public class CalificarTarea extends Command {

    private String estudianteID;
    private String cursoID;
    private String tareaID;
    private Integer calificacion;
    private String retroalimentacion;

    public CalificarTarea(String estudianteID, String cursoID, String tareaID, Integer calificacion, String retroalimentacion) {
        this.estudianteID = estudianteID;
        this.cursoID = cursoID;
        this.tareaID = tareaID;
        this.calificacion = calificacion;
        this.retroalimentacion = retroalimentacion;
    }

    public CalificarTarea() {
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
