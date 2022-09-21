package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.backend.domain.identifiers.CursoID;

import java.util.Set;


public class AgregarCurso extends Command {
    // TODO: Este comando no es detonado directamente por el front.
    //  El comando CrearCurso detona este comando para hacer
    //  vincular al profesor con el ID del curso nuevo.

    private String ProfesorId;
    private String cursoID;

    public String getProfesorId() {
        return ProfesorId;
    }

    public void setProfesorId(String profesorId) {
        ProfesorId = profesorId;
    }

    public String getCursoID() {
        return cursoID;
    }

    public void setCursoID(String cursoID) {
        this.cursoID = cursoID;
    }
}
