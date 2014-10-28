package me.hqm.infractions;

import java.util.UUID;

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
