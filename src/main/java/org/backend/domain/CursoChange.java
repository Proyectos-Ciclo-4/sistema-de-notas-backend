package org.backend.domain;

import co.com.sofka.domain.generic.EventChange;
import org.backend.domain.events.CursoCreado;

import java.util.HashMap;

public class CursoChange extends EventChange {

    public CursoChange(Curso curso) {

        apply((CursoCreado event) -> {
            curso.titulo = event.getTitulo();
            curso.profesorID = event.getProfesorID();
            curso.temas = event.getTemas();
        });

    }
}
