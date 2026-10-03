package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory implementation of IStorage.
 *
 * It will associate each GAV coordinate with its corresponding Artifact.
 */
public class InMemoryStorage implements IStorage {
    /**
     * Stores artifacts in memory.
     *
     * Key   = GAV coordinate
     * Value = corresponding Artifact
     */
    private final Map<Gav, Artifact> artifacts = new HashMap<>();

    @Override
    public void put(Gav gav, Artifact artifact) {

        // Store the artifact using its GAV coordinate as the key.
        artifacts.put(gav, artifact);
    }

    @Override
    public Optional<Artifact> get(Gav gav) {

        // Search the map using the GAV coordinate.
        Artifact artifact = artifacts.get(gav);

        // If artifact exists, return an Optional containing it.
        // If it does not exist, return Optional.empty().
        return Optional.ofNullable(artifact);
    }
}
