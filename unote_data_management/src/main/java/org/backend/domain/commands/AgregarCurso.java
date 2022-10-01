package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.backend.domain.identifiers.CursoID;

import java.util.Set;


public class AgregarCurso extends Command {
    // TODO: Este comando no es detonado directamente por el front.
    //  El comando CrearCurso detona este comando para hacer
    //  vincular al profesor con el ID del curso nuevo.

    private String profesorID;
    private String cursoID;

    public AgregarCurso(String profesorId, String cursoID) {
        this.profesorID = profesorId;
        this.cursoID = cursoID;
    }

    public String getProfesorID() {
        return profesorID;
    }

    public void setProfesorID(String profesorId) {
        profesorID = profesorId;
    }

    public String getCursoID() {
        return cursoID;
    }

    public void setCursoID(String cursoID) {
        this.cursoID = cursoID;
    }
}
