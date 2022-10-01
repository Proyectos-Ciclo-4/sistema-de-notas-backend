package org.backend.domain.identifiers;

import co.com.sofka.domain.generic.Identity;

public class InscripcionID extends Identity {
    public InscripcionID(String uuid) {
        super(uuid);
    }

    public InscripcionID() {
    }

    public static InscripcionID of(String uuid) {
        return new InscripcionID(uuid);
    }
}
