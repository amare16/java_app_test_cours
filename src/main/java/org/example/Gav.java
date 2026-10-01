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
        String[] parts = value.split(":");

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
