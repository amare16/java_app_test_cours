package org.example;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class StorageBasedRegistryTest {

    // Manual Stub of IStorage
    static class StorageStub implements IStorage {

        private Artifact storedArtifact;

        StorageStub(Artifact storedArtifact) {
            this.storedArtifact = storedArtifact;
        }

        @Override
        public Optional<Artifact> get(Gav gav) {
            if (storedArtifact != null &&
                    storedArtifact.gav().equals(gav)) {
                return Optional.of(storedArtifact);
            }
            return Optional.empty();
        }

        @Override
        public void put(Gav gav, Artifact artifact) {
            storedArtifact = artifact;
        }
    }

    @Test
    void publishAlreadyExistingArtifactThrowsException() {

        // Create the unique coordinate of an artifact.
        Gav gav = Gav.parse("org.acme:lib-a:1.0.0");

        // Create an artifact with no dependencies.
        Artifact artifact = new Artifact(gav, Set.of());

        // Simulate storage where this artifact already exists.
        IStorage storage = new StorageStub(artifact);

        // Create the registry and inject our manual Stub.
        StorageBasedRegistry registry = new StorageBasedRegistry(storage);

        // Verify that publishing the same artifact throws an exception.
        assertThrows(
                AlreadyPublishedException.class,
                () -> registry.publish(artifact)
        );
    }

    @Test
    void publishNewArtifactStoresIt() {

        // Create the coordinate of a new artifact.
        Gav gav = Gav.parse("org.acme:lib-b:1.0.0");

        // Create an artifact without dependencies.
        Artifact artifact = new Artifact(gav, Set.of());

        // Create an empty manual storage Stub.
        StorageStub storage = new StorageStub(null);

        // Inject the Stub into the registry.
        StorageBasedRegistry registry = new StorageBasedRegistry(storage);

        // Publish the new artifact.
        registry.publish(artifact);

        // Verify that the artifact was saved in storage.
        assertEquals(Optional.of(artifact), storage.get(gav));
    }

    @Test
    void lookupExistingArtifactReturnsIt() {

        // Create the artifact's unique coordinate.
        Gav gav = Gav.parse("org.acme:lib-a:1.0.0");

        // Create an artifact without dependencies.
        Artifact artifact = new Artifact(gav, Set.of());

        // Create a Stub that already contains the artifact.
        IStorage storage = new StorageStub(artifact);

        // Inject the Stub into the registry.
        StorageBasedRegistry registry = new StorageBasedRegistry(storage);

        // Search for the artifact using its GAV.
        Optional<Artifact> result = registry.lookup(gav);

        // Verify that lookup returns the expected artifact.
        assertEquals(Optional.of(artifact), result);
    }

    @Test
    void lookupMissingArtifactReturnsEmpty() {

        // Create the coordinate of an artifact we want to find.
        Gav gav = Gav.parse("org.acme:missing:1.0.0");

        // Create an empty storage Stub.
        IStorage storage = new StorageStub(null);

        // Inject the empty Stub into our registry.
        StorageBasedRegistry registry = new StorageBasedRegistry(storage);

        // Search for the artifact.
        Optional<Artifact> result = registry.lookup(gav);

        // Verify that no artifact was found.
        assertEquals(Optional.empty(), result);
    }
}