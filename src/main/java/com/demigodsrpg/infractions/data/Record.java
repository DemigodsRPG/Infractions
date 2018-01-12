package com.demigodsrpg.infractions.data;

import com.demigodsrpg.infractions.InfractionsM;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.query.Query;

import java.util.*;

@Entity("records")
public class Record {

    // -- IMPORTANT DATA -- //

    @Id
    ObjectId id;
    Integer value;
    String reason;
    String player;
    String issuer;

    // -- CONSTRUCTOR -- //

    public Record(int value, String reason, UUID player, UUID issuer) {
        this.value = value;
        this.reason = reason;
        this.player = player.toString();
        this.issuer = issuer.toString();
    }

    // -- GETTERS -- //

    public Date getDate() {
        return id.getDate();
    }

    public int getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }

    public UUID getPlayerId() {
        return UUID.fromString(player);
    }

    public UUID getIssuerId() {
        return UUID.fromString(issuer);
    }

    public List<Proof> getProof() {
        Datastore db = InfractionsM.getDatastore();
        Query<Proof> query = db.createQuery(Proof.class).field("record").equal(this); // TODO does this work?
        return query.asList();
    }
}
