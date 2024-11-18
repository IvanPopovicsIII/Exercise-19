package com.example.exercise_19.app;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.exercise_19.handler.InputHandler;
import com.example.exercise_19.handler.OutputHandler;
import com.example.exercise_19.model.DivisorRule;
import com.example.exercise_19.model.Range;
import com.example.exercise_19.service.NumberPrinterService;

/**
 * Controller class to orchestrate the program flow.
 */
@Component
public class Controller {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final NumberPrinterService service;
    
    @Value("${output.filepath}")
    private String filePath;

    public Controller(InputHandler inputHandler, OutputHandler outputHandler, NumberPrinterService service) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.service = service;
    }
    

    /**
     * Runs the application logic: gathering input, processing, and outputting results.
     */
    public void run() {
        // Get input from the user
        Range range = inputHandler.getRange();
        List<DivisorRule> divisorRules = inputHandler.getDivisorRules();

        // Process the input
        List<String> results = service.processRange(range, divisorRules);

        // Ask user where to print the results
        System.out.println("Do you want to save the results to a file? (yes/no)");
        String choice = inputHandler.getScanner().nextLine().trim().toLowerCase();

        if ("yes".equals(choice)) {
           
            try {
                outputHandler.printToFile(results);
                System.out.println("Results saved to file: " + filePath);
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } else if ("no".equals(choice)) {
            // If the user chooses to print to the console
            outputHandler.printToConsole(results);  // Print results to console
            System.out.println("Results printed to console.");
        } else {
            // If the input is invalid (not 'yes' or 'no')
            System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
        }
    }
}
