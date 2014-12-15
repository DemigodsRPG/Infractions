package com.demigodsrpg.infractions.spigot;

import com.demigodsrpg.infractions.InfractionsPlayer;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public final class SpigotPlayer extends InfractionsPlayer {
    private final OfflinePlayer player;

    // -- CONSTRUCTOR -- //

    public SpigotPlayer(OfflinePlayer player) {
        if (player == null) {
            throw new NullPointerException("Player cannot be null.");
        }
        this.player = player;
    }

    // -- SEND -- //

    @Override
    public void sendMessage(String message) {
        if (player.isOnline()) {
            player.getPlayer().sendMessage(message);
        }
    }

    @Override
    public void sendMessage(String[] message) {
        if (player.isOnline()) {
            player.getPlayer().sendMessage(message);
        }
    }

    @Override
    public void kick(String message) {
        if (player.isOnline()) {
            player.getPlayer().kickPlayer(message);
        }
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
        if (player.isOnline()) {
            return player.getPlayer().getDisplayName();
        }
        return player.getName();
    }

    @Override
    public boolean isOnline() {
        return player.isOnline();
    }
}
