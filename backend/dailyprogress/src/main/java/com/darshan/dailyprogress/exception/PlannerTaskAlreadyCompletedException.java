package com.darshan.dailyprogress.exception;

public class PlannerTaskAlreadyCompletedException extends RuntimeException {

    public PlannerTaskAlreadyCompletedException(String message) {
        super(message);
    }
}