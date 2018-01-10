package com.demigodsrpg.infractions.data;

import com.demigodsrpg.infractions.util.UrlUtil;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.Date;
import java.util.UUID;

@Entity("proof")
public class Proof {

    // -- IMPORTANT DATA -- //

    @Id
    ObjectId id;
    @Reference(lazy = true)
    Record record;
    String url;
    String shortenedUrl;
    String issuer;

    // -- CONSTRUCTOR -- //

    public Proof(Record record, String url, UUID issuer) throws IllegalArgumentException {
        if (!UrlUtil.isValidUrl(url)) {
            throw new IllegalArgumentException("Provided proof url is not valid.");
        }
        this.record = record;
        this.url = url;
        this.shortenedUrl = UrlUtil.shortenUrl(url);
        this.issuer = issuer.toString();
    }

    // -- GETTERS -- //

    public Date getDate() {
        return id.getDate();
    }

    public Record getRecord() {
        return record;
    }

    public String getUrl() {
        return url;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public UUID getIssuerId() {
        return UUID.fromString(issuer);
    }
}
