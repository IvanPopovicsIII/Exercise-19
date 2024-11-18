package com.example.exercise_19.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.exercise_19.model.DivisorRule;
import com.example.exercise_19.model.Range;

/**
 * Service for processing and printing numbers with specified rules.
 */
@Service
public class NumberPrinterService {

    /**
     * Processes a range of numbers and applies divisor rules.
     *
     * @param range        The range of numbers to process.
     * @param divisorRules The list of rules to apply.
     * @return A list of strings representing the processed results.
     */
    public List<String> processRange(Range range, List<DivisorRule> divisorRules) {
        return IntStream.rangeClosed(range.getStart(), range.getEnd())
                .mapToObj(i -> {
                    // Apply rules to each number
                    StringBuilder result = new StringBuilder();
                    if (i == 0) {
                        return "0"; // Special case for 0
                    }

                    for (DivisorRule rule : divisorRules) {
                        if (i % rule.getDivisor() == 0) {
                            result.append(rule.getMessage());
                        }
                    }

                    // Return the number if no rules apply
                    return result.length() > 0 ? result.toString() : String.valueOf(i);
                })
                .collect(Collectors.toList());
    }
}