package org.unote_sockets.domain;

import co.com.sofka.domain.generic.EventChange;
import org.unote_sockets.domain.events.CursoAgregado;
import org.unote_sockets.domain.events.ProfesorCreado;

import java.util.HashSet;

public class ProfesorChange extends EventChange {
    public ProfesorChange(Profesor profesor){
        apply((ProfesorCreado event) -> {
            profesor.nombre = event.getNombre();
            profesor.cursos = new HashSet<>();
        });

        apply((CursoAgregado event) -> {
            profesor.agregarIdsDeCurso(event.getCursoID());
        });
    }
}
