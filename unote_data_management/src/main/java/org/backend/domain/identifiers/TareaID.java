package org.backend.domain.identifiers;

import co.com.sofka.domain.generic.Identity;

public class TareaID extends Identity {

    public TareaID(String uuid) {
        super(uuid);
    }

    public TareaID() {

    }

    public static TareaID of(String uuid) {
        return new TareaID(uuid);
    }
}
