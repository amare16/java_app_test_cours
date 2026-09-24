plugins {
    java
    jacoco
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    // JUnit 5
        testImplementation(platform("org.junit:junit-bom:5.12.1"))
        testImplementation("org.junit.jupiter:junit-jupiter")

        // Hamcrest
        testImplementation("org.hamcrest:hamcrest:2.2")

        // Mockito
        testImplementation("org.mockito:mockito-core:5.14.2")
}

tasks.test {
    useJUnitPlatform()
}