package org.backend.domain.identifiers;

import co.com.sofka.domain.generic.Identity;

public class ProfesorID extends Identity {
    public ProfesorID(String uuid) {
        super(uuid);
    }

    public ProfesorID() {
    }

    public static ProfesorID of(String uuid) {
        return new ProfesorID(uuid);
    }
}
