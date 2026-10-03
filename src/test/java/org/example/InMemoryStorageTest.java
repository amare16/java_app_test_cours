package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for InMemoryStorage.
 *
 * Each test starts with a new, empty storage.
 */
public class InMemoryStorageTest {
    // We use the interface type, as requested by the TD.
    // This keeps the test dependent on the IStorage contract rather than directly on the implementation.
    private IStorage storage;

    @BeforeEach
    void init() {
        // Before every test, create a fresh storage.
        // This prevents one test from affecting another test.
        storage = new InMemoryStorage();
    }

    @Test
    void putThenGetShouldReturnStoredArtifact() {
        // Create the coordinate that will be used as the storage key.
        Gav gav = Gav.parse("org.acme:lib-a:1.0.0");

        // Create an artifact with no dependencies.
        Artifact artifact = new Artifact(gav, Set.of());

        // Store the artifact using its GAV coordinate.
        storage.put(gav, artifact);

        // Retrieve the artifact using the same coordinate.
        Optional<Artifact> result = storage.get(gav);

        // The Optional should contain an artifact.
        assertTrue(result.isPresent());

        // The retrieved artifact should be the one we stored.
        assertEquals(artifact, result.get());
    }

    @Test
    void getShouldReturnEmptyWhenArtifactDoesNotExist() {

        // Create a valid GAV that has NOT been stored.
        Gav gav = Gav.parse("org.acme:unknown:1.0.0");

        // Search for the absent coordinate.
        Optional<Artifact> result = storage.get(gav);

        // The storage should explicitly represent "not found"
        // with Optional.empty().
        assertTrue(result.isEmpty());
    }
}
