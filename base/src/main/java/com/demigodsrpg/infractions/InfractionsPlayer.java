package com.demigodsrpg.infractions;

import java.util.UUID;

/**
 * A simple abstract class to interact with a player in a supported game.
 */
public abstract class InfractionsPlayer {
    // -- SEND INFORMATION -- //
    public abstract void sendMessage(String message);

    public abstract void sendMessage(String[] message);

    public abstract void kick(String message);

    // -- GET INFORMATION -- //
    public abstract UUID getUniqueId();

    public abstract String getName();

    public abstract String getDisplayName();

    public abstract boolean isOnline();

    public boolean shouldBan(Backend backend) {
        return getPlayerRecord(backend).getScore() >= backend.getConfig().maxScore();
    }

    // -- GET INFRACTION INFO -- //
    public PlayerRecord getPlayerRecord(Backend backend) {
        return new PlayerRecord(backend, getUniqueId().toString());
    }
}
