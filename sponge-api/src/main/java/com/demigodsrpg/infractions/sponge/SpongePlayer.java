package com.demigodsrpg.infractions.sponge;

import com.demigodsrpg.infractions.InfractionsPlayer;
import com.google.common.base.Optional;
import org.spongepowered.api.Game;
import org.spongepowered.api.entity.player.Player;

import java.util.UUID;

public final class SpongePlayer extends InfractionsPlayer {
    private final Game game;
    private final Player player;

    public SpongePlayer(Game game, UUID playerId) {
        this.game = game;
        Optional<Player> player = game.getPlayer(playerId);
        if (!player.isPresent()) {
            throw new NullPointerException("Player cannot be null.");
        }
        this.player = player.get();
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    @Override
    public void sendMessage(String[] message) {
        player.sendMessage(message);
    }

    @Override
    public void kick(String message) {
        // FIXME player.kick(message);
    }

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
        return player.isOnline();
    }

    @Deprecated
    public Game getGame() {
        return game;
    }
}
