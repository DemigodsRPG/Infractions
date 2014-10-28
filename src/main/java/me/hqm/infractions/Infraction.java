package me.hqm.infractions;

import com.iciql.Iciql;

import javax.annotation.concurrent.Immutable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Infractions are immutable, simple records of a player's behavior.
 */

@Immutable
@Iciql.IQTable(name = "infraction")
public class Infraction {
    // -- IMPORTANT DATA -- //

    @Iciql.IQColumn(name = "key", primaryKey = true, autoIncrement = true)
    int key;
    @Iciql.IQColumn(name = "value")
    int value;
    @Iciql.IQColumn(name = "time")
    Timestamp timeStamp;
    @Iciql.IQColumn(name = "active")
    boolean active;
    @Iciql.IQColumn(name = "reason", length = 255)
    String reason;
    @Iciql.IQColumn(name = "proof", length = 510)
    String proof;
    @Iciql.IQColumn(name = "player_id", length = 255)
    String player;

    // -- CONSTRUCTORS -- //

    public Infraction() {
        // Empty constructor for Iciql.
    }

    public Infraction(int key, int value, long time, boolean active, String reason, String proof, UUID player) {
        this.key = key;
        this.value = value;
        timeStamp = new Timestamp(time);
        this.active = active;
        this.reason = reason;
        this.proof = proof;
        this.player = player.toString();
    }

    // -- GETTERS -- //

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public boolean isActive() {
        return active;
    }

    public String getReason() {
        return reason;
    }

    public String getProof() {
        return proof;
    }

    public String getRawPlayer() {
        return player;
    }

    public UUID getPlayer() {
        return UUID.fromString(player);
    }
}

