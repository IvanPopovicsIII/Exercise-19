package com.example.exercise_19.model;

import java.util.Objects;

/**
 * Represents a divisor and the message to be printed when divisible.
 */
public class DivisorRule {
    private final int divisor;
    private final String message;

    public DivisorRule(int divisor, String message) {
        this.divisor = divisor;
        this.message = message;
    }

    public int getDivisor() {
        return divisor;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivisorRule that = (DivisorRule) o;
        return divisor == that.divisor && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(divisor, message);
    }
    
}