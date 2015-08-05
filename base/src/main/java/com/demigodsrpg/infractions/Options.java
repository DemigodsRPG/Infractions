package com.demigodsrpg.infractions;

/**
 * Interface for a config object.
 */
public interface Options {
    String serverName();

    String databaseUrl();

    int maxScore();

    boolean requireProof();

    boolean useBitly();

    String bitlyUser();

    String bitlyKey();

    boolean canKick();

    String warnMessage();

    String banMessage();
}
