package org.unote_sockets.models.generics;

import java.util.Date;

public class StoredEvent {

    private String eventBody;
    private Date ocurredOn;
    private String typeName;

    public StoredEvent() {
    }

    public StoredEvent(String eventBody, Date ocurredOn, String typeName) {
        this.eventBody = eventBody;
        this.ocurredOn = ocurredOn;
        this.typeName = typeName;
    }

    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    public Date getOcurredOn() {
        return ocurredOn;
    }

    public void setOcurredOn(Date ocurredOn) {
        this.ocurredOn = ocurredOn;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
