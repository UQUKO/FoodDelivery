package com.example.task;

public class CustomError extends Exception {

    /**
     * Constructs a new CustomError with the specified detail message.
     *
     * The detail message is a string that describes the error condition or the reason why the error occurred.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public CustomError(String message) {
        super(message);
    }
}

