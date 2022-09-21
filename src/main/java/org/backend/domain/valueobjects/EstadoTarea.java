package org.backend.domain.valueobjects;

import co.com.sofka.domain.generic.ValueObject;

public class EstadoTarea implements ValueObject<Boolean> {
    public final Boolean estadoTarea;

    public EstadoTarea(Boolean estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    @Override
    public Boolean value() {
        return estadoTarea;
    }
}
