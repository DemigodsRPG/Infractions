package com.demigodsrpg.infractions.bungee.impl;

import com.demigodsrpg.infractions.InfractionsPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

@SuppressWarnings("deprecation")
public final class BungeePlayer extends InfractionsPlayer {
    private final ProxiedPlayer player;

    // -- CONSTRUCTOR -- //

    public BungeePlayer(ProxiedPlayer player) {
        if (player == null) {
            throw new NullPointerException("Player cannot be null.");
        }
        this.player = player;
    }

    // -- SEND -- //

    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    @Override
    public void sendMessage(String[] messages) {
        for (String message : messages) {
            player.sendMessages(message);
        }
    }

    @Override
    public void kick(String message) {
        player.disconnect(message);
    }

    // -- GET -- //

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public String getDisplayName() {
        return player.getDisplayName();
    }

    @Override
    public boolean isOnline() {
        return true;
    }
}
