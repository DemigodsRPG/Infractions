package com.demigodsrpg.infractions.bungee.impl;

import com.demigodsrpg.infractions.InfractionsConfig;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class BungeeConfig implements InfractionsConfig {
    private Configuration configuration;

    public BungeeConfig(Plugin plugin, File file) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                InputStream is = plugin.getResourceAsStream("config.yml");
                OutputStream os = new FileOutputStream(file);
                ByteStreams.copy(is, os);
            } catch (Exception e) {
                throw new RuntimeException("Unable to create configuration file", e);
            }
        }
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (Exception oops) {
            oops.printStackTrace();
        }
    }

    @Override
    public String databaseUrl() {
        return configuration.getString("database.url");
    }

    @Override
    public int maxScore() {
        return configuration.getInt("max.score");
    }

    @Override
    public boolean canKick() {
        return configuration.getBoolean("can.kick");
    }

    @Override
    public String warnMessage() {
        return configuration.getString("message.warn");
    }

    @Override
    public String banMessage() {
        return configuration.getString("message.ban");
    }
}
