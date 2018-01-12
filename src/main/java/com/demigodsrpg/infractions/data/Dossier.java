package com.demigodsrpg.infractions.data;

import com.demigodsrpg.infractions.*;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class Dossier {

    private String playerId;

    // -- CONSTRUCTORS -- //

    public Dossier(String playerId) {
        this.playerId = playerId;
    }

    public Dossier(Player player) {
        this(player.getUniqueId().toString());
    }

    // -- MUTATORS -- //

    public RecordModifyResult saveRecord(Record record) {
        int before = getScore();

        Datastore db = InfractionsDB.getDatastore();
        db.save(record);

        return processChange(before);
    }

    public RecordModifyResult deleteRecord(Record record) {
        int before = getScore();

        Datastore db = InfractionsDB.getDatastore();
        db.delete(record);

        return processChange(before);
    }

    private RecordModifyResult processChange(int before) {
        OfflinePlayer offline = Bukkit.getOfflinePlayer(playerId);
        Options config = InfractionsP.getOptions();

        if (config.maxScore() - getScore() <= 0) {
            String banMessage = config.banMessage();
            if (offline.isOnline()) {
                if (config.canKick()) {
                    offline.getPlayer().kickPlayer(banMessage);
                } else {
                    offline.getPlayer().sendMessage(banMessage);
                }
            }
            return RecordModifyResult.SHOULD_BAN;
        } else if (getScore() < before) {
            String warnMessage = config.warnMessage();
            warnMessage = warnMessage.replace("%reason%", "HAX"); // FIXME
            if (offline.isOnline()) {
                if (config.canKick()) {
                    offline.getPlayer().kickPlayer(warnMessage);
                } else {
                    offline.getPlayer().sendMessage(warnMessage);
                }
            }
            return RecordModifyResult.WARNED;
        } else if (getScore() > before) {
            return RecordModifyResult.FORGIVEN;
        } else {
            return RecordModifyResult.NO_CHANGE;
        }
    }

    // -- GETTERS -- //

    public String getPlayerId() {
        return playerId;
    }

    public List<Record> getInfractions() {
        Datastore db = InfractionsDB.getDatastore();
        Query<Record> query = db.createQuery(Record.class).field("player").equal(playerId);
        return query.asList();
    }

    public int getScore() {
        int score = InfractionsP.getOptions().maxScore();
        for (Record infraction : getInfractions()) {
            score -= infraction.getValue();
        }
        return score;
    }
}
