package com.dtu.security.prototype2.server.authentication;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Ticket implements Serializable {
    private final int lifespan = 20000;
    private long timestamp; //when it was created
    private String username;
    private String uniqueID;

    public Ticket(long timestamp, String username) {
        this.timestamp = timestamp;
        this.username = username;
        uniqueID = UUID.randomUUID().toString();
    }

    public boolean equalTicket(Ticket t) {
        return (this.timestamp == t.timestamp) && (Objects.equals(this.username, t.username)) && (Objects.equals(this.uniqueID, t.uniqueID));
    }

    public boolean isActive() {
        return System.currentTimeMillis() <= timestamp + lifespan;
    }

    public int getLifespan() {
        return lifespan;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getUniqueID() {
        return uniqueID;
    }
}
