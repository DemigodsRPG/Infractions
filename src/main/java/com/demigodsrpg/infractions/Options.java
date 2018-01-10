package com.demigodsrpg.infractions;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Options {
    private FileConfiguration configuration;

    public Options(Plugin plugin) {
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        configuration = plugin.getConfig();
    }

    public String serverName() {
        return configuration.getString("server", "My Minecraft Server");
    }

    public String databaseUrl() {
        return "jdbc:" + configuration.getString("database.url", "mongodb://localhost");
    }

    public int maxScore() {
        return configuration.getInt("max.score", 100);
    }

    public boolean requireProof() {
        return configuration.getBoolean("proof.require", true);
    }

    public boolean useBitly() {
        return configuration.getBoolean("proof.bitly.use", true);
    }

    public String bitlyUser() {
        return configuration.getString("proof.bitly.username", "");
    }

    public String bitlyKey() {
        return configuration.getString("proof.bitly.key", "");
    }

    public boolean canKick() {
        return configuration.getBoolean("can.kick", true);
    }

    public String warnMessage() {
        return configuration.getString("message.warn", "You have recieved an infraction. Reason: %reason%.");
    }

    public String banMessage() {
        return configuration.getString("message.ban", "You have been banned for reaching the maximum infractions score.");
    }

    public static Configuration getConfig() {
        return InfractionsP.INST.getConfig();
    }
}
