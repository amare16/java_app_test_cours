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
}