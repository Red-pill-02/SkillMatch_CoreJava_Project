package com.niit.skillmatch.exception;

/**
 * Custom exception thrown when a task assignment
 * is attempted without any applicants.
 */
public class NoApplicantException extends Exception {

    public NoApplicantException(String message) {
        super(message);
    }
}
