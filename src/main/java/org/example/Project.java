package org.example;

import java.util.Set;

/**
 * Represents a project described by a minibuild file.
 *
 * @param name         name of the project
 * @param dependencies direct dependencies declared by the project
 */
public record Project(String name, Set<Gav> dependencies) {
}
