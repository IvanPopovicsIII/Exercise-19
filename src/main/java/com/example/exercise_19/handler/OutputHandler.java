package com.example.exercise_19.handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handles output for the application.
 */
@Component
public class OutputHandler {
	
    private final BufferedWriter writer;

    // Constructor Injection: injecting FileWriter which will be used to create BufferedWriter
    @Autowired
    public OutputHandler(FileWriter fileWriter) {
        this.writer = new BufferedWriter(fileWriter); // Wrap the FileWriter in BufferedWriter
    }
    
    // Constructor Injection for testing
    public OutputHandler(BufferedWriter writer) {
        this.writer = writer;
    }

    /**
     * Prints the processed results to the console.
     *
     * @param results A list of strings to be printed.
     */
    public void printToConsole(List<String> results) {
        results.forEach(System.out::println);
    }

    /**
     * Writes the processed results to a file.
     *
     * @param results A list of strings to be written to the file.
     * @throws IOException If an I/O error occurs.
     */
    public void printToFile(List<String> results) throws IOException {
        
        // Write the results to the file
        try  {
            for (String result : results) {
                writer.write(result);
                writer.newLine();
            }
        } finally {
        	writer.close();
        }
        
    }
}