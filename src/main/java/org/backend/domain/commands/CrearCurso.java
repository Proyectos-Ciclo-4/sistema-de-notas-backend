package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.identifiers.TemaID;
import org.backend.domain.valueobjects.Titulo;

import java.util.Set;

public class CrearCurso extends Command {
    private final CursoID cursoID;
    private final Titulo titulo;
    private final ProfesorID profesorID;
    private final Set<TemaID> tema_id;


    public CrearCurso(CursoID cursoID, Titulo titulo, ProfesorID profesorID, Set<TemaID> tema_id) {
        this.cursoID = cursoID;
        this.titulo = titulo;
        this.profesorID = profesorID;
        this.tema_id = tema_id;
    }

    public CursoID getCursoID() {
        return cursoID;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public ProfesorID getProfesorID() {
        return profesorID;
    }

    public Set<TemaID> getTema_id() {
        return tema_id;
    }
}
