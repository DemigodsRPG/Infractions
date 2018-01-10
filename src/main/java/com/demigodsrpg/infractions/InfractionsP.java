package com.demigodsrpg.infractions;

import com.demigodsrpg.chitchat.Chitchat;
import com.demigodsrpg.infractions.chitchat.ReputationTag;
import com.demigodsrpg.infractions.command.*;
import com.demigodsrpg.infractions.data.Proof;
import com.demigodsrpg.infractions.data.Record;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class InfractionsP extends JavaPlugin {

    // -- STATIC OBJECTS -- //

    static InfractionsP INST;
    static Options OPTIONS;
    static Morphia MORPHIA;
    static Datastore DATA;

    // -- INITIALIZE -- //

    @Override
    public void onEnable() {
        INST = this;

        // Initialize MongoDB
        initMongo();

        // Commands
        getCommand("infractions").setExecutor(new InfractionsCommand());
        getCommand("cite").setExecutor(new CiteCommand());
        getCommand("uncite").setExecutor(new UnciteCommand());
        getCommand("history").setExecutor(new HistoryCommand());

        // Register Chitchat tags
        if (Bukkit.getPluginManager().isPluginEnabled("Chitchat")) {
            Chitchat.getChatFormat().add(new ReputationTag(OPTIONS));
        }
    }

    private void initMongo() {
        // Setup Morphia
        MORPHIA = new Morphia();
        MORPHIA.map(Record.class, Proof.class);

        // Setup Datastore
        MongoClientURI conn = new MongoClientURI(OPTIONS.databaseUrl());
        DATA = MORPHIA.createDatastore(new MongoClient(conn), "infractions");
        DATA.ensureIndexes();
    }

    // -- DISABLE -- //

    @Override
    public void onDisable() {

    }

    // -- STATIC GETTERS -- //

    public static InfractionsP getInstance() {
        return INST;
    }

    public static Datastore getDatastore() {
        return DATA;
    }

    public static Options getOptions() {
        return OPTIONS;
    }
}
