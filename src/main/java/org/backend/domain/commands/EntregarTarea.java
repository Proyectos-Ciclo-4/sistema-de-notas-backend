package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class EntregarTarea extends Command {
    String estudianteID;
    String cursoID;
    String tareaID;
    String URLArchivo;

    public EntregarTarea(String estudianteID, String cursoID, String tareaID, String URLArchivo) {
        this.estudianteID = estudianteID;
        this.cursoID = cursoID;
        this.tareaID = tareaID;
        this.URLArchivo = URLArchivo;
    }

    public EntregarTarea() {
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

    public String getURLArchivo() {
        return URLArchivo;
    }
}
