package com.demigodsrpg.infractions.spigot.impl;

import com.demigodsrpg.infractions.Options;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class SpigotOptions implements Options {
    private FileConfiguration configuration;

    public SpigotOptions(Plugin plugin) {
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        configuration = plugin.getConfig();
    }

    @Override
    public String databaseUrl() {
        return configuration.getString("database.url", "postgresql://localhost:5432/minecraft?user=minecraft&password=minecraft");
    }

    @Override
    public int maxScore() {
        return configuration.getInt("max.score", 100);
    }

    @Override
    public boolean requireProof() {
        return configuration.getBoolean("proof.require", true);
    }

    @Override
    public boolean useBitly() {
        return configuration.getBoolean("proof.bitly.use", true);
    }

    @Override
    public String bitlyUser() {
        return configuration.getString("proof.bitly.username", "");
    }

    @Override
    public String bitlyKey() {
        return configuration.getString("proof.bitly.key", "");
    }

    @Override
    public boolean canKick() {
        return configuration.getBoolean("can.kick", true);
    }

    @Override
    public String warnMessage() {
        return configuration.getString("message.warn", "You have recieved an infraction. Reason: %reason%.");
    }

    @Override
    public String banMessage() {
        return configuration.getString("message.ban", "You have been banned for reaching the maximum infractions score.");
    }
}
