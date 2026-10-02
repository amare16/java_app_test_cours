package org.example;

public class Gav {
    private final String group;
    private final String artifact;
    private final String version;

    public Gav(String group, String artifact, String version) {
        this.group = group;
        this.artifact = artifact;
        this.version = version;
    }
    public static Gav parse(String value) {
        // Split the coordinate using ":".
        // The -1 is important: it keeps empty values at the end.
        // Example:
        // "org.acme:lib-a:" -> ["org.acme", "lib-a", ""]
        String[] parts = value.split(":", -1);

        // A valid GAV must contain exactly 3 parts:
        // group : artifact : version
        //
        // This rejects coordinates with too few or too many parts.
        if (parts.length != 3) {
            throw new IllegalArgumentException("A GAV must contain exactly 3 parts");
        }

        // None of the three parts is allowed to be empty.
        // Examples rejected:
        // ":lib-a:1.0.0"   -> missing group
        // "org.acme::1.0.0" -> missing artifact
        // "org.acme:lib-a:" -> missing version
        if (parts[0].isEmpty()
                || parts[1].isEmpty()
                || parts[2].isEmpty()) {
            throw new IllegalArgumentException("GAV parts must not be empty");
        }

        // The coordinate is valid, so create the Gav object.
        // parts[0] = group
        // parts[1] = artifact
        // parts[2] = version
        return new Gav(parts[0], parts[1], parts[2]);
    }
    public String group() {
        return group;
    }
    public String artifact() {
        return artifact;
    }
    public String version() {
        return version;
    }
}
