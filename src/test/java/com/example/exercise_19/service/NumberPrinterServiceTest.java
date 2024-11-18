package com.example.exercise_19.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import com.example.exercise_19.model.DivisorRule;
import com.example.exercise_19.model.Range;

class NumberPrinterServiceTest {

    private NumberPrinterService numberPrinterService;

    @BeforeEach
    void setUp() {
        numberPrinterService = new NumberPrinterService();
    }

    @Test
    void testProcessRange_WithDivisorRules() {
        // Setup test data
        Range range = new Range(1, 10);
        List<DivisorRule> rules = Arrays.asList(
                new DivisorRule(3, "Fizz"),
                new DivisorRule(5, "Buzz")
        );

        // Call the method
        List<String> result = numberPrinterService.processRange(range, rules);

        // Verify results
        assertEquals(Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz"), result);
    }

    @Test
    void testProcessRange_NoDivisorRules() {
        // Setup test data
        Range range = new Range(1, 5);
        List<DivisorRule> rules = Arrays.asList();

        // Call the method
        List<String> result = numberPrinterService.processRange(range, rules);

        // Verify results
        assertEquals(Arrays.asList("1", "2", "3", "4", "5"), result);
    }

    @Test
    void testProcessRange_SingleRule() {
        // Setup test data
        Range range = new Range(1, 5);
        List<DivisorRule> rules = Arrays.asList(
                new DivisorRule(2, "Even")
        );

        // Call the method
        List<String> result = numberPrinterService.processRange(range, rules);

        // Verify results
        assertEquals(Arrays.asList("1", "Even", "3", "Even", "5"), result);
    }

    @Test
    void testProcessRange_ZeroCase() {
        // Setup test data
        Range range = new Range(0, 5);
        List<DivisorRule> rules = Arrays.asList(
                new DivisorRule(2, "Even")
        );

        // Call the method
        List<String> result = numberPrinterService.processRange(range, rules);

        // Verify that 0 is treated as a special case
        assertEquals(Arrays.asList("0", "1", "Even", "3", "Even", "5"), result);
    }

    @Test
    void testProcessRange_WithNegativeNumbers() {
        // Setup test data
        Range range = new Range(-5, 5);
        List<DivisorRule> rules = Arrays.asList(
                new DivisorRule(2, "Even"),
                new DivisorRule(3, "DivBy3")
        );

        // Call the method
        List<String> result = numberPrinterService.processRange(range, rules);

        // Verify results
        assertEquals(
                Arrays.asList("-5", "Even", "DivBy3", "Even", "-1", "0", "1", "Even", "DivBy3", "Even", "5"),
                result
        );
    }
    
    @Test
    void testProcessRange_NoMatchesForAnyRule() {
        // Setup test data
        Range range = new Range(1, 5);
        List<DivisorRule> rules = Arrays.asList(
                new DivisorRule(6, "DivisibleBySix")
        );

        // Call the method
        List<String> result = numberPrinterService.processRange(range, rules);

        // Verify results: No number in the range should match the rule
        assertEquals(Arrays.asList("1", "2", "3", "4", "5"), result);
    }
}