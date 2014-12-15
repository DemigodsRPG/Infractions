package com.demigodsrpg.infractions.spigot;

import com.demigodsrpg.infractions.Backend;
import com.demigodsrpg.infractions.InfractionsConfig;
import com.demigodsrpg.infractions.InfractionsPlayer;
import com.iciql.Db;
import org.bukkit.Bukkit;

import java.util.UUID;

public class SpigotBackend implements Backend {
    private final InfractionsConfig config;

    public SpigotBackend(InfractionsConfig config) {
        this.config = config;
    }

    @Override
    public InfractionsConfig getConfig() {
        return config;
    }

    @Override
    public Db openDb() {
        return Db.open(config.databaseUrl());
    }

    @Override
    public InfractionsPlayer getPlayer(String playerId) {
        return new SpigotPlayer(Bukkit.getOfflinePlayer(UUID.fromString(playerId)));
    }
}
