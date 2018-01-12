package com.demigodsrpg.infractions;

import com.demigodsrpg.chitchat.Chitchat;
import com.demigodsrpg.infractions.chitchat.ReputationTag;
import com.demigodsrpg.infractions.command.*;
import com.demigodsrpg.util.LibraryHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class InfractionsP extends JavaPlugin {

    // -- STATIC OBJECTS -- //

    static InfractionsP INST;
    static Options OPTIONS;

    // -- INITIALIZE -- //

    @Override
    public void onEnable() {
        INST = this;

        // Handle Libraries
        LibraryHandler lib = new LibraryHandler(this);
        lib.addMavenLibrary(LibraryHandler.MAVEN_CENTRAL, Depends.ORG_MONGO, Depends.MONGODB, Depends.MONGODB_VER);
        lib.addMavenLibrary(LibraryHandler.MAVEN_CENTRAL, Depends.ORG_MONGO_MORPHIA, Depends.MORPHIA,
                Depends.MORPHIA_VER);
        lib.addMavenLibrary(LibraryHandler.MAVEN_CENTRAL, Depends.COM_ROSALOVES, Depends.BITLYJ, Depends.BITLYJ_VER);

        // Initialize MongoDB
        InfractionsM.initMongo();

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

    // -- DISABLE -- //

    @Override
    public void onDisable() {

    }

    // -- STATIC GETTERS -- //

    public static InfractionsP getInstance() {
        return INST;
    }

    public static Options getOptions() {
        return OPTIONS;
    }
}
