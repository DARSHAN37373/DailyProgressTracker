package com.darshan.dailyprogress.exception;

public class HabitAlreadyCompletedException extends RuntimeException {

    public HabitAlreadyCompletedException(String message) {
        super(message);
    }
}