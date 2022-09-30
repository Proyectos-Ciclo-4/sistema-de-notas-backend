package org.unote_sockets.domain;

import co.com.sofka.domain.generic.EventChange;
import org.backend.domain.events.CursoCreado;

public class CursoChange extends EventChange {

    public CursoChange(Curso curso) {

        apply((CursoCreado event) -> {
            curso.titulo = event.getTitulo();
            curso.profesorID = event.getProfesorID();
            curso.temas = event.getTemas();
        });

    }
}
