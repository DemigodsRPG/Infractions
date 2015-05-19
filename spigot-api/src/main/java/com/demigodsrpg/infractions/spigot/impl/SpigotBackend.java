package com.demigodsrpg.infractions.spigot.impl;

import com.demigodsrpg.infractions.Backend;
import com.demigodsrpg.infractions.Human;
import com.demigodsrpg.infractions.Options;
import com.iciql.Db;
import org.bukkit.Bukkit;

import java.util.UUID;

public class SpigotBackend implements Backend {
    private final Options options;

    public SpigotBackend(Options options) {
        this.options = options;
    }

    @Override
    public Options getOptions() {
        return options;
    }

    @Override
    public Db openDb() {
        return Db.open(options.databaseUrl());
    }

    @Override
    public Human getPlayer(String playerId) {
        return new SpigotPlayer(Bukkit.getOfflinePlayer(UUID.fromString(playerId)));
    }
}
