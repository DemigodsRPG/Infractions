package me.hqm.infractions;

import java.util.UUID;

/**
 * A simple interface to interact with a player in a supported game.
 */

public interface IPlayer {
    // -- SEND INFORMATION -- //
    void sendMessage(String message);
    void sendMessage(String[] message);

    // -- GET INFORMATION -- //
    UUID getUniqueId();
    String getName();
    String getDisplayName();
    boolean isOnline();
}
