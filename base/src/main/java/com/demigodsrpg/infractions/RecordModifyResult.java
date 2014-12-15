package com.demigodsrpg.infractions;

/**
 * Result state returned after giving/removing an infraction from a player.
 */
public enum RecordModifyResult {
    /**
     * The player should be banned (per server ban via a spigot plugin).
     */
    SHOULD_BAN,
    /**
     * The player has been warned.
     */
    WARNED,
    /**
     * The player has been forgiven.
     */
    FORGIVEN,
    /**
     * Nothing has changed.
     */
    NO_CHANGE,
    /**
     * There was an error.
     */
    ERROR
}
