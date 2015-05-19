package com.demigodsrpg.infractions.sponge;

import com.demigodsrpg.infractions.Backend;
import com.demigodsrpg.infractions.Human;
import com.demigodsrpg.infractions.Options;
import com.iciql.Db;
import org.spongepowered.api.Game;

import java.util.UUID;

public class SpongeBackend implements Backend {
    private final Game game;
    private final Options config;

    public SpongeBackend(Game game, Options config) {
        this.game = game;
        this.config = config;
    }

    @Override
    public Options getOptions() {
        return config;
    }

    @Override
    public Db openDb() {
        return Db.open(config.databaseUrl());
    }

    @Override
    public Human getPlayer(String playerId) {
        return new SpongePlayer(game, UUID.fromString(playerId));
    }
}
