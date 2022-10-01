package org.unote_sockets.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

public class FechaLimite implements ValueObject<String> {
    private final String fechaLimite;

    public FechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    @Override
    public String value() {
        return fechaLimite;
    }
}
