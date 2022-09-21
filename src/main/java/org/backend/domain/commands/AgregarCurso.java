package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.backend.domain.identifiers.CursoID;

import java.util.Set;

public class AgregarCurso extends Command {
    private String ProfesorId;
    private Set<CursoID> cursos;

    public String getProfesorId() {
        return ProfesorId;
    }

    public void setProfesorId(String profesorId) {
        ProfesorId = profesorId;
    }

    public Set<CursoID> getCursos() {
        return cursos;
    }

    public void setCursos(Set<CursoID> cursos) {
        this.cursos = cursos;
    }
}
