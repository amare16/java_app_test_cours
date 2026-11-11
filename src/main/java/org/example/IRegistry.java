package org.example;

import java.util.Optional;

// Contract for publishing and searching for artifacts.
public interface IRegistry {

    // Publish an artifact.
    // Throw an exception if its GAV has already been published.
    void publish(Artifact artifact)
            throws AlreadyPublishedException;

    // Search for an artifact using its GAV.
    // Return Optional.empty() if no artifact is found.
    Optional<Artifact> lookup(Gav gav);
}