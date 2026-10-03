package org.example;

import java.util.Set;

/**
 * Represents an artifact in the minibuild system.
 *
 * @param gav          coordinate of the artifact
 * @param dependencies coordinates of its dependencies
 */
public record Artifact(Gav gav, Set<Gav> dependencies) {
}
