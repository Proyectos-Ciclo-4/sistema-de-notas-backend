package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class PromedioActualizado extends DomainEvent {
    private String cursoID;
    private Float promedio;

    public PromedioActualizado(String cursoID, Float promedio) {
        super("domain.promedioActualizado");
        this.cursoID = cursoID;
        this.promedio = promedio;
    }

    public String getCursoID() {
        return cursoID;
    }

    public Float getPromedio() {
        return promedio;
    }
}
