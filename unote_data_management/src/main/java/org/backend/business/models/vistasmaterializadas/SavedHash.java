package org.backend.business.models.vistasmaterializadas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavedHash {

    private String hash;

    private String typeName;

    private String aggregateID;

    public SavedHash(String hash, String typeName, String aggregateID) {
        this.hash = hash;
        this.typeName = typeName;
        this.aggregateID = aggregateID;
    }
}
