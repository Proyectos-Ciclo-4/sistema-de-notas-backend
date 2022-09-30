package org.unote_sockets.domain.identifiers;

import co.com.sofka.domain.generic.Identity;

public class CursoID extends Identity {
    public CursoID(String uuid) {
        super(uuid);
    }

    public CursoID() {
    }

    public static CursoID of(String uuid) {
        return new CursoID(uuid);
    }
}
