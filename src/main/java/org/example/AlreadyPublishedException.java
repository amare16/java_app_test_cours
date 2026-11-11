package org.example;

// Exception thrown when an artifact's GAV is already published.
public class AlreadyPublishedException extends RuntimeException {

    // Constructor: receives a message explaining the error.
    public AlreadyPublishedException(String message) {
        // Pass the message to the parent exception class.
        super(message);
    }
}