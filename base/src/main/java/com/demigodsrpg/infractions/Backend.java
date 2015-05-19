package com.demigodsrpg.infractions;

import com.iciql.Db;

public interface Backend {
    Options getOptions();

    Db openDb();

    Human getPlayer(String playerId);
}
