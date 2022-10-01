package org.unote_sockets.domain.identifiers;

import co.com.sofka.domain.generic.Identity;

public class TemaID extends Identity {
    public TemaID(String uuid) {
        super(uuid);
    }

    public TemaID() {
    }

    public static TemaID of(String uuid) {
        return new TemaID(uuid);
    }
}
