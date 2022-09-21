package org.backend.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Date;

public class FechaLimite implements ValueObject<Date> {
    private final Date fechaLimite;

    public FechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    @Override
    public Date value() {
        return fechaLimite;
    }
}
