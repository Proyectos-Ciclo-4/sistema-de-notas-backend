package org.backend.domain.identifiers;

import co.com.sofka.domain.generic.Identity;

public class EstudianteID extends Identity {
    public EstudianteID(String uuid) {
        super(uuid);
    }

    public EstudianteID() {
    }

    public static EstudianteID of(String uuid) {
        return new EstudianteID(uuid);
    }
}
