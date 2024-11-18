package com.example.exercise_19.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OutputHandlerTest {

    private OutputHandler outputHandler;

    @Mock
    private BufferedWriter mockWriter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        outputHandler = new OutputHandler(mockWriter);
    }

    @Test
    void testPrintToConsole() {
        // Test data
        List<String> results = Arrays.asList("line1", "line2", "line3");

        // Redirect System.out to capture console output
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));

        // Call the method
        outputHandler.printToConsole(results);

        // Normalize the console output to remove any trailing spaces or newlines
        String actualOutput = consoleOutput.toString().replaceAll("\r\n", "\n"); // Normalize line endings

        // Expected output with consistent line breaks
        String expectedOutput = "line1\nline2\nline3\n";

        // Assert the console output
        assertEquals(expectedOutput, actualOutput);

        // Reset System.out to default
        System.setOut(System.out);
    }

    @Test
    void testPrintToFile() throws IOException {
        // Test data
        List<String> results = Arrays.asList("line1", "line2", "line3");

        // Call the method
        outputHandler.printToFile(results);

        // Verify that each line is written exactly once
        verify(mockWriter, times(1)).write("line1");
        verify(mockWriter, times(1)).write("line2");
        verify(mockWriter, times(1)).write("line3");

        // Verify newLine is called for each result
        verify(mockWriter, times(3)).newLine();

        // Verify that writer is closed
        verify(mockWriter, times(1)).close();
    }

    @Test
    void testPrintToFile_IOException() throws IOException {
        // Test data
        List<String> results = Arrays.asList("line1", "line2");

        // Simulate an IOException
        doThrow(new IOException("Mocked IO Exception")).when(mockWriter).write(anyString());

        // Exception handling test
        try {
            outputHandler.printToFile(results);
        } catch (IOException e) {
            // Assert the exception message
            assertEquals("Mocked IO Exception", e.getMessage());
        }

        // Verify that close is still attempted
        verify(mockWriter).close();
    }

    // Additional test to ensure the file writing doesn't happen during console output
    @Test
    void testRun_WithConsoleOutput() throws IOException {
        // Setup mock input: User chooses console output
        // You would mock the user input and mock interaction with other components (e.g., InputHandler)
        // For simplicity, assume that the user has chosen console output, so we will call printToConsole directly

        List<String> results = Arrays.asList("line1", "line2", "line3");

        // Mock the behavior of the OutputHandler
        outputHandler.printToConsole(results);  // Directly test the behavior here

        // Verify printToFile is not called
        verify(mockWriter, times(0)).write(anyString());
    }

    // Additional test to ensure the file writing happens during file output
    @Test
    void testRun_WithFileOutput() throws IOException {
        // Setup mock input: User chooses file output
        List<String> results = Arrays.asList("line1", "line2", "line3");

        // Call the method
        outputHandler.printToFile(results);

        // Verify file writing method was called
        verify(mockWriter, times(1)).write("line1");
        verify(mockWriter, times(1)).write("line2");
        verify(mockWriter, times(1)).write("line3");
    }
}