package org.unote_sockets.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

public class Titulo implements ValueObject<String> {
    private final String titulo;

    public Titulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String value() {
        return titulo;
    }
}
