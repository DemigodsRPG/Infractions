package com.demigodsrpg.infractions;

import com.iciql.Db;

import java.util.List;

public final class PlayerRecord {
    private final Backend backend;
    private final String playerId;

    // -- CONSTRUCTOR -- //

    public PlayerRecord(Backend backend, String playerId) {
        this.backend = backend;
        this.playerId = playerId;
    }

    // -- MUTATORS -- //

    public RecordModifyResult giveInfraction(Backend backend, Infraction infraction) {
        int before = getScore();

        Db db = backend.openDb();
        db.insert(infraction);
        db.close();

        return processChange(before);
    }

    public RecordModifyResult updateInfraction(Backend backend, Infraction infraction) {
        int before = getScore();

        Db db = Db.open(backend.getConfig().databaseUrl());
        db.update(infraction);
        db.close();

        return processChange(before);
    }

    public RecordModifyResult removeInfraction(Backend backend, Infraction infraction) {
        int before = getScore();

        Db db = backend.openDb();
        db.delete(infraction);
        db.close();

        return processChange(before);
    }

    private RecordModifyResult processChange(int before) {
        InfractionsPlayer player = backend.getPlayer(playerId);
        InfractionsConfig config = backend.getConfig();

        if (getScore() > config.maxScore()) {
            player.kick(config.banMessage());
            return RecordModifyResult.SHOULD_BAN;
        } else if (getScore() > before) {
            String warnMessage = config.warnMessage();
            if (config.canKick()) {
                player.kick(warnMessage);
            } else {
                player.sendMessage(warnMessage);
            }
            return RecordModifyResult.WARNED;
        } else if (getScore() < before) {
            return RecordModifyResult.FORGIVEN;
        } else {
            return RecordModifyResult.NO_CHANGE;
        }
    }

    // -- GETTERS -- //

    public Backend getBackend() {
        return backend;
    }

    public String getPlayerId() {
        return playerId;
    }

    public List<Infraction> getInfractions() {
        Infraction alias = new Infraction();
        Db db = backend.openDb();

        try {
            return db.from(alias).where(alias.player).is(playerId).select();
        } finally {
            db.close();
        }
    }

    public int getScore() {
        int score = 0;
        for (Infraction infraction : getInfractions()) {
            score += infraction.getValue();
        }
        return score;
    }
}
