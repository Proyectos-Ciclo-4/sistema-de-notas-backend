package org.backend.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.events.CursoAgregado;
import org.backend.domain.events.ProfesorCreado;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.valueobjects.Nombre;

import java.util.List;
import java.util.Set;

public class Profesor extends AggregateEvent<ProfesorID> {

    //propiedades
    protected ProfesorID profesorID;
    protected Nombre nombre;
    protected Set<CursoID> cursos;

    //constructores
    public Profesor(ProfesorID id,  Nombre nombre) {
        super(id);
        subscribe(new ProfesorChange(this));
        appendChange(new ProfesorCreado(id, nombre)).apply();
    }

    public Profesor(ProfesorID id){
        super(id);
        subscribe(new ProfesorChange(this));
    }

    public static Profesor from(ProfesorID id, List<DomainEvent> events){
        var profesor = new Profesor(id);
        events.forEach(profesor::applyEvent);
        return profesor;
    }


    //comportamientos
    public void agregarCurso(CursoID cursoID){
        appendChange(new CursoAgregado(cursoID)).apply();
    }

    //modificadores
    public void agregarIdsDeCurso(CursoID id){
        cursos.add(id);
    }

}
