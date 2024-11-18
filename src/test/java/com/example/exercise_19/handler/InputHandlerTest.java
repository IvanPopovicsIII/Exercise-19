package com.example.exercise_19.handler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.exercise_19.model.DivisorRule;
import com.example.exercise_19.model.Range;

class InputHandlerTest {

    private Scanner mockScanner;
    private InputHandler inputHandler;

    @BeforeEach
    void setUp() {
        mockScanner = mock(Scanner.class); // Mock Scanner
        inputHandler = new InputHandler(mockScanner); // Inject mock Scanner
    }

    @Test
    void testGetRange() {
        // Mock user input for range
        when(mockScanner.nextLine()).thenReturn("1", "10");

        // Call the method
        Range range = inputHandler.getRange();

        // Verify the results
        assertEquals(1, range.getStart());
        assertEquals(10, range.getEnd());

        // Verify interactions with Scanner
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void testGetDivisorRules() {
        // Mock user input for divisor rules
        when(mockScanner.nextLine())
            .thenReturn("2 foo", "3 bar", "done");

        // Call the method
        List<DivisorRule> rules = inputHandler.getDivisorRules();

        // Verify the results
        assertEquals(2, rules.size());
        assertEquals(2, rules.get(0).getDivisor());
        assertEquals("foo", rules.get(0).getMessage());
        assertEquals(3, rules.get(1).getDivisor());
        assertEquals("bar", rules.get(1).getMessage());

        // Verify interactions with Scanner
        verify(mockScanner, times(3)).nextLine();
    }

    @Test
    void testGetDivisorRules_NoRulesEntered() {
        // Mock user input: immediately enter "done"
        when(mockScanner.nextLine()).thenReturn("done");

        // Call the method
        List<DivisorRule> rules = inputHandler.getDivisorRules();

        // Verify that the returned list is empty
        assertTrue(rules.isEmpty());

        // Verify interaction with Scanner
        verify(mockScanner, times(1)).nextLine();
    }

    @Test
    void testGetDivisorRules_InvalidInput() {
        // Mock user input: invalid input followed by valid rules
        when(mockScanner.nextLine())
            .thenReturn("invalid", "2 foo", "done");

        // Call the method
        List<DivisorRule> rules = inputHandler.getDivisorRules();

        // Verify that the valid rule was captured
        assertEquals(1, rules.size());
        assertEquals(2, rules.get(0).getDivisor());
        assertEquals("foo", rules.get(0).getMessage());

        // Verify interactions with Scanner
        verify(mockScanner, times(3)).nextLine();
    }

    @Test
    void testClose() {
        // Call the close method
        inputHandler.close();

        // Verify that the Scanner's close method was called
        verify(mockScanner).close();
    }
}