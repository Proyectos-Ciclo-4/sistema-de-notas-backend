package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.entities.Inscripcion;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.valueobjects.Nombre;

import java.util.HashMap;

public class EstudianteCreado extends DomainEvent {

    private Nombre nombre;

    private HashMap<CursoID, Inscripcion> cursos;

    public EstudianteCreado() {
        super("unote.estudianteCreado");
    }

    public EstudianteCreado(Nombre nombre, HashMap<CursoID, Inscripcion> cursos) {
        super("unote.estudianteCreado");
        this.nombre = nombre;
        this.cursos = cursos;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public HashMap<CursoID, Inscripcion> getCursos() {
        return cursos;
    }

    @Override
    public String toString() {
        return "EstudianteCreado{" +
                "nombre=" + nombre +
                ", cursos=" + cursos +
                '}';
    }
}
