package org.backend.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

public class Promedio implements ValueObject<Float> {
    private final Float promedio;

    public Promedio(Float promedio) {
        this.promedio = promedio;
    }

    @Override
    public Float value() {
        return promedio;
    }
}
