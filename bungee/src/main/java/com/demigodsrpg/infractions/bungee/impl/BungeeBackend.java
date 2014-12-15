package com.demigodsrpg.infractions.bungee.impl;

import com.demigodsrpg.infractions.Backend;
import com.demigodsrpg.infractions.InfractionsConfig;
import com.demigodsrpg.infractions.InfractionsPlayer;
import com.iciql.Db;
import net.md_5.bungee.api.ProxyServer;

import java.util.UUID;

public class BungeeBackend implements Backend {
    private final BungeeConfig config;

    public BungeeBackend(BungeeConfig config) {
        this.config = config;
    }

    @Override
    public InfractionsConfig getConfig() {
        return config;
    }

    @Override
    public Db openDb() {
        return Db.open(getConfig().databaseUrl());
    }

    @Override
    public InfractionsPlayer getPlayer(String playerId) {
        return new BungeePlayer(ProxyServer.getInstance().getPlayer(UUID.fromString(playerId)));
    }
}
