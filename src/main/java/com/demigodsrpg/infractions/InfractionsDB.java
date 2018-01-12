package com.demigodsrpg.infractions;

import com.demigodsrpg.infractions.data.Proof;
import com.demigodsrpg.infractions.data.Record;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import static com.demigodsrpg.infractions.InfractionsP.OPTIONS;

public class InfractionsDB {

    // -- STATIC OBJECTS -- //

    static Morphia MORPHIA;
    static Datastore DATA;

    // -- INITIALIZE -- //

    static void initMongo() {
        // Setup Morphia
        MORPHIA = new Morphia();
        MORPHIA.map(Record.class, Proof.class);

        // Setup Datastore
        MongoClientURI conn = new MongoClientURI(OPTIONS.databaseUrl());
        DATA = MORPHIA.createDatastore(new MongoClient(conn), "infractions");
        DATA.ensureIndexes();
    }

    // -- STATIC GETTERS -- //

    public static Datastore getDatastore() {
        return DATA;
    }
}
