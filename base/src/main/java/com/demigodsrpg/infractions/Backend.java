package com.demigodsrpg.infractions;

import com.iciql.Db;

public interface Backend {
    InfractionsConfig getConfig();

    Db openDb();

    InfractionsPlayer getPlayer(String playerId);
}
