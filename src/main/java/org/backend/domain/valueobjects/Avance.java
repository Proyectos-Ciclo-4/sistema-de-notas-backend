package org.backend.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

public class Avance implements ValueObject<Long> {
    private final Long avance;

    public Avance(Long avance) {
        this.avance = avance;
    }

    @Override
    public Long value() {
        return avance;
    }
}
