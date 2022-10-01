package org.unote_sockets.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

public class Avance implements ValueObject<Float> {
    private final Float avance;

    public Avance(Float avance) {
        this.avance = avance;
    }

    @Override
    public Float value() {
        return avance;
    }
}
