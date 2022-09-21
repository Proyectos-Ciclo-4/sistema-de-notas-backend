package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.valueobjects.Nombre;

public class ProfesorCreado extends DomainEvent {
    private final ProfesorID id;
    private final Nombre nombre;

    public ProfesorCreado(ProfesorID id, Nombre nombre) {
        super("profesorCreado");
        this.id = id;
        this.nombre = nombre;
    }

    public Nombre getNombre() {
        return nombre;
    }
}
