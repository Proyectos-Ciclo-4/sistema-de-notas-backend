package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.valueobjects.Porcentaje;

public class PorcentajeDeTareaActualizado extends DomainEvent {
    private TareaID id;
    private Float porcentaje;

    public PorcentajeDeTareaActualizado(TareaID id, Float porcentaje) {
        super("unote.PorcentajeDeTareaActualizado");
        this.id = id;
        this.porcentaje = porcentaje;
    }

    public TareaID getTareaId() {
        return id;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }
}
