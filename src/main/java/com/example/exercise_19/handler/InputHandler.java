package com.example.exercise_19.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.example.exercise_19.model.DivisorRule;
import com.example.exercise_19.model.Range;

@Component
public class InputHandler {

    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }
    
    // Constructor injection for Scanner
    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads the range from the user.
     *
     * @return A Range object containing the start and end values.
     */
    public Range getRange() {
        System.out.println("Enter start of range:");
        int start = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter end of range:");
        int end = Integer.parseInt(scanner.nextLine());

        return new Range(start, end);
    }

    /**
     * Reads divisor rules from the user.
     *
     * @return A list of DivisorRule objects.
     */
    public List<DivisorRule> getDivisorRules() {
        List<DivisorRule> divisorRules = new ArrayList<>();
        System.out.println("Enter a divisor and message (e.g., '2 foo'). Type 'done' to finish:");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            try {
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Input must contain exactly two parts: a number and a message.");
                }
                int divisor = Integer.parseInt(parts[0]);
                String message = parts[1];
                divisorRules.add(new DivisorRule(divisor, message));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. The first part must be a valid integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return divisorRules;
    }
    
    /**
     * Returns the scanner resource
     */
    public Scanner getScanner() {
    	return this.scanner;
    }

    /**
     * Closes the scanner resource.
     */
    public void close() {
        scanner.close();
    }
}
