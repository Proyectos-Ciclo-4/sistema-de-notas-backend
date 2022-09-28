package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

public class EntregarTarea extends Command {
    String estudianteID;
    String cursoID;
    String tareaID;
    String archivoURL;

    public EntregarTarea(String estudianteID, String cursoID, String tareaID, String archivoURL) {
        this.estudianteID = estudianteID;
        this.cursoID = cursoID;
        this.tareaID = tareaID;
        this.archivoURL = archivoURL;
    }

    public EntregarTarea() {
    }

    public String getEstudianteID() {
        return estudianteID;
    }

    public void setEstudianteID(String estudianteID) {
        this.estudianteID = estudianteID;
    }

    public String getCursoID() {
        return cursoID;
    }

    public void setCursoID(String cursoID) {
        this.cursoID = cursoID;
    }

    public String getTareaID() {
        return tareaID;
    }

    public void setTareaID(String tareaID) {
        this.tareaID = tareaID;
    }

    public String getArchivoURL() {
        return archivoURL;
    }

    public void setArchivoURL(String archivoURL) {
        this.archivoURL = archivoURL;
    }
}
