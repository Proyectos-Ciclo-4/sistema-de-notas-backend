package org.backend.application.repository;

import org.backend.business.models.vistasmaterializadas.generics.StoredEvent;

public class DocumentEventStored {

    private String id;
    private String aggregateRootId;
    private StoredEvent storedEvent;

    public DocumentEventStored() {

    }

    public DocumentEventStored(String aggregateRootId, StoredEvent storedEvent) {
        this.aggregateRootId = aggregateRootId;
        this.storedEvent = storedEvent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAggregateRoodId() {
        return aggregateRootId;
    }

    public void setAggregateRoodId(String aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    public StoredEvent getStoredEvent() {
        return storedEvent;
    }

    public void setStoredEvent(StoredEvent storedEvent) {
        this.storedEvent = storedEvent;
    }
}
