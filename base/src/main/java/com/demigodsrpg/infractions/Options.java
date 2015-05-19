package com.demigodsrpg.infractions;

/**
 * Interface for a config object.
 */
public interface Options {
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
