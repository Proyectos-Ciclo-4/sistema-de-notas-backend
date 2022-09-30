package org.unote_sockets.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.unote_sockets.domain.identifiers.ProfesorID;
import org.unote_sockets.domain.valueobjects.Nombre;

public class ProfesorCreado extends DomainEvent {
    private final ProfesorID id;
    private final Nombre nombre;

    public ProfesorCreado(ProfesorID id, Nombre nombre) {
        super("unote.profesorCreado");
        this.id = id;
        this.nombre = nombre;
    }

    public Nombre getNombre() {
        return nombre;
    }
}
