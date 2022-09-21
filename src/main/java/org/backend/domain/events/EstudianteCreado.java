package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.entities.Inscripcion;
import org.backend.domain.identifiers.CursoID;

import java.util.HashMap;

public class EstudianteCreado extends DomainEvent {

    private String nombre;

    private HashMap<CursoID, Inscripcion> cursos;

    public EstudianteCreado() {
        super("domain.estudianteCreado");
    }

    public EstudianteCreado(String nombre, HashMap<CursoID, Inscripcion> cursos) {
        super("domain.estudianteCreado");
        this.nombre = nombre;
        this.cursos = cursos;
    }

    public String getNombre() {
        return nombre;
    }

    public HashMap<CursoID, Inscripcion> getCursos() {
        return cursos;
    }
}
