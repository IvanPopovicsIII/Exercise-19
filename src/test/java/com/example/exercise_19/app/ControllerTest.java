package com.example.exercise_19.app;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.exercise_19.handler.InputHandler;
import com.example.exercise_19.handler.OutputHandler;
import com.example.exercise_19.model.DivisorRule;
import com.example.exercise_19.model.Range;
import com.example.exercise_19.service.NumberPrinterService;

class ControllerTest {

    @Mock
    private InputHandler inputHandler;

    @Mock
    private OutputHandler outputHandler;

    @Mock
    private NumberPrinterService service;

    @InjectMocks
    private Controller controller;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outContent));  // Redirect System.out
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);  // Restore System.out after the test
    }
    
    @Test
    void testRun_SavesToFile() throws IOException {
        // Mock input
        Range range = new Range(1, 10);
        List<DivisorRule> divisorRules = Arrays.asList(new DivisorRule(2, "Foo"), new DivisorRule(3, "Bar"));
        List<String> results = Arrays.asList("1", "Foo", "Bar", "Foo", "5", "FooBar", "7", "Foo", "Bar", "Foo");

        // Mocking input behavior
        when(inputHandler.getRange()).thenReturn(range);
        when(inputHandler.getDivisorRules()).thenReturn(divisorRules);

        // Mocking service behavior
        when(service.processRange(range, divisorRules)).thenReturn(results);

        // Mock user input for saving to file ("yes" means save to file)
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("yes"); // Simulate user choosing "yes" for saving to file
        when(inputHandler.getScanner()).thenReturn(mockScanner);

        // Run the method
        controller.run();

        // Verify the correct interactions
        // The outputHandler should print to file (not console)
        verify(outputHandler).printToFile(results);
        
        // Ensure printToConsole is never called
        verify(outputHandler, never()).printToConsole(anyList());

        // Ensure Scanner's nextLine() is called at least once
        verify(mockScanner, times(1)).nextLine();
    }

    @Test
    void testRun_PrintsToConsole() {
        // Mock input
        Range range = new Range(1, 10);
        List<DivisorRule> divisorRules = Arrays.asList(new DivisorRule(2, "Foo"), new DivisorRule(3, "Bar"));
        List<String> results = Arrays.asList("1", "Foo", "Bar", "Foo", "5", "FooBar", "7", "Foo", "Bar", "Foo");

        // Mocking input behavior
        when(inputHandler.getRange()).thenReturn(range);
        when(inputHandler.getDivisorRules()).thenReturn(divisorRules);

        // Mocking service behavior
        when(service.processRange(range, divisorRules)).thenReturn(results);

        // Mock user input for printing to console
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("no");
        when(inputHandler.getScanner()).thenReturn(mockScanner);

        // Run the method
        controller.run();

        // Verify interactions
        verify(outputHandler).printToConsole(results);
        try {
			verify(outputHandler, never()).printToFile(anyList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    void testRun_HandlesIOException() throws IOException {
        // Mock input
        Range range = new Range(1, 10);
        List<DivisorRule> divisorRules = Arrays.asList(new DivisorRule(2, "Foo"), new DivisorRule(3, "Bar"));
        List<String> results = Arrays.asList("1", "Foo", "Bar", "Foo", "5", "FooBar", "7", "Foo", "Bar", "Foo");

        // Mocking input behavior
        when(inputHandler.getRange()).thenReturn(range);
        when(inputHandler.getDivisorRules()).thenReturn(divisorRules);

        // Mocking service behavior
        when(service.processRange(range, divisorRules)).thenReturn(results);

        // Mock user input for saving to file
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("yes");
        when(inputHandler.getScanner()).thenReturn(mockScanner);

        // Simulate IOException when writing to file
        doThrow(new IOException("File write error")).when(outputHandler).printToFile(results);

        // Run the method
        controller.run();

        // Verify that an error message was printed
        verify(outputHandler, never()).printToConsole(anyList());
    }
}