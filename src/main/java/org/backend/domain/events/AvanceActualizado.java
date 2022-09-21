package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class AvanceActualizado extends DomainEvent {
    private String cursoID;
    private Float avance;

    public AvanceActualizado(String cursoID, Float avance) {
        super("domain.avanceActualizado");
        this.cursoID = cursoID;
        this.avance = avance;
    }

    public String getCursoID() {
        return cursoID;
    }

    public Float getAvance() {
        return avance;
    }
}
