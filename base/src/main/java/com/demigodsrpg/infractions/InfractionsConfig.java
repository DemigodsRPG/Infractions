package com.demigodsrpg.infractions;

/**
 * Interface for a config object.
 */
public interface InfractionsConfig {
    String databaseUrl();

    int maxScore();

    boolean canKick();

    String warnMessage();

    String banMessage();
}
