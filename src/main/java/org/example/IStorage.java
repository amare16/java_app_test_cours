package org.example;


import java.util.Optional;

/**
 * Interface for storing and retrieving artifacts.
 *
 * A Gav coordinate acts as the key,
 * and an Artifact is the value associated with that key.
 */
public interface IStorage {
    // Store an artifact using its GAV coordinate as the key.
    void put(Gav gav, Artifact artifact);

    // Search for an artifact using its GAV coordinate.
    //
    // Optional.empty() means that no artifact exists
    // for the requested coordinate.
    Optional<Artifact> get(Gav gav);
}
