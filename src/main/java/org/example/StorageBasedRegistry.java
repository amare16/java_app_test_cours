package org.example;

import java.util.Optional;

// Registry that uses IStorage to manage published artifacts.
public class StorageBasedRegistry implements IRegistry {

    // Storage dependency, supplied through the constructor.
    private final IStorage storage;

    // Constructor: inject the storage implementation or test Stub.
    public StorageBasedRegistry(IStorage storage) {
        this.storage = storage;
    }

    @Override
    public void publish(Artifact artifact)
            throws AlreadyPublishedException {

        // Get the unique GAV coordinate of the artifact.
        Gav gav = artifact.gav();

        // Check whether an artifact with this GAV already exists.
        if (storage.get(gav).isPresent()) {

            // Refuse to publish the same coordinate twice.
            throw new AlreadyPublishedException(
                    "Artifact already published: " + gav
            );
        }

        // Save the artifact when its GAV is not already present.
        storage.put(gav, artifact);
    }

    @Override
    public Optional<Artifact> lookup(Gav gav) {

        // Delegate the search to the storage component.
        return storage.get(gav);
    }
}