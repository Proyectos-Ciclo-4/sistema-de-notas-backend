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
    protected ProfesorID profesorID;
    protected Nombre nombre;
    protected Set<CursoID> cursos;

    public Profesor(ProfesorID id,  Nombre nombre) {
        super(id);
        appendChange(new ProfesorCreado(id, nombre)).apply();
    }

    public Profesor(ProfesorID id){
        super(id);
        subscribe(new ProfesorEventChange(this));
    }

    public static Profesor from(ProfesorID id, List<DomainEvent> events){
        var profesor = new Profesor(id);
        events.forEach(profesor::applyEvent);
        return profesor;
    }


    public void AgregarCurso(ProfesorID id, Set<CursoID> cursos){
        appendChange(new CursoAgregado(profesorID, cursos)).apply();
    }


}
