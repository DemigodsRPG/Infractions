package com.demigodsrpg.infractions.sponge;

import com.demigodsrpg.infractions.Human;
import com.google.common.base.Optional;
import org.spongepowered.api.Game;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Texts;

import java.util.UUID;

public final class SpongePlayer extends Human {
    private final Game game;
    private final Player player;

    public SpongePlayer(Game game, UUID playerId) {
        this.game = game;
        Optional<Player> player = game.getServer().getPlayer(playerId);
        if (!player.isPresent()) {
            throw new NullPointerException("Player cannot be null.");
        }
        this.player = player.get();
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(Texts.of(message));
    }

    @Override
    public void sendMessage(String[] message) {
        player.sendMessage(Texts.of(message));
    }

    @Override
    public void kick(String message) {
        player.kick(Texts.of(message));
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
        return Texts.toLegacy(player.getDisplayNameData().getDisplayName());
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
