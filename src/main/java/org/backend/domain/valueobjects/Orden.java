package org.backend.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

public class Orden implements ValueObject<Integer> {
    private final Integer orden;

    public Orden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public Integer value() {
        return orden;
    }
}
