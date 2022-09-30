package org.unote_sockets.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

public class Porcentaje implements ValueObject<Float> {
    private final Float porcentaje;

    public Porcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public Float value() {
        return porcentaje;
    }
}
