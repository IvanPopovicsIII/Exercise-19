package com.example.exercise_19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.exercise_19.app.Controller;

/**
 * Main entry point for the Exercise 19 application.
 */
@SpringBootApplication
public class Exercise19Application implements CommandLineRunner {

	  @Autowired
	  private Controller controller;  // Autowire the controller
	
	   public static void main(String[] args) {
	        SpringApplication.run(Exercise19Application.class, args);  // Launching Spring ApplicationContext
	    }

	   @Override
	    public void run(String... args) throws Exception {
	        controller.run();  // Explicitly invoke the run method of your controller
	    }
	   
}
