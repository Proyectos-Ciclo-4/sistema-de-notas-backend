package org.unote_sockets.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

public class Nombre implements ValueObject<String> {
    private final String nombre;

    public Nombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String value() {
        return nombre;
    }
}
