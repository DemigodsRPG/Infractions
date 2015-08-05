package com.demigodsrpg.infractions;

import com.iciql.Iciql;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Infractions are immutable and simple records of a player's behavior.
 */
@Iciql.IQTable(name = "infraction")
public final class Infraction {
    // -- IMPORTANT DATA -- //

    @Iciql.IQColumn(name = "key", primaryKey = true, autoIncrement = true)
    int key;
    @Iciql.IQColumn(name = "value")
    int value;
    @Iciql.IQColumn(name = "time")
    Timestamp timeStamp;
    @Iciql.IQColumn(name = "origin", length = 255)
    String origin;
    @Iciql.IQColumn(name = "reason", length = 255)
    String reason;
    @Iciql.IQColumn(name = "proof", length = 510)
    String proof;
    @Iciql.IQColumn(name = "player_id", length = 255)
    String player;
    @Iciql.IQColumn(name = "issuer_id", length = 255)
    String issuer;

    // -- CONSTRUCTORS -- //

    public Infraction() {
        // Empty constructor for Iciql.
    }

    public Infraction(int value, long time, String origin, String reason, String proof, UUID player, UUID issuer) {
        this.value = value;
        timeStamp = new Timestamp(time);
        this.origin = origin;
        this.reason = reason;
        this.proof = proof;
        this.player = player.toString();
        this.issuer = issuer.toString();
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

    public String getOrigin() {
        return origin;
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

    public UUID getIssuer() {
        return UUID.fromString(issuer);
    }

    public PlayerRecord getPlayerRecord(Backend backend) {
        return new PlayerRecord(backend, player);
    }
}

