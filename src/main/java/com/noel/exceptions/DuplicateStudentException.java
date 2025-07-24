package com.noel.exceptions;

public class DuplicateStudentException extends Exception{
    public DuplicateStudentException(String message) {
        super(message);
    }

    public DuplicateStudentException(String message, Throwable cause) {
        super(message, cause);
    }
}
