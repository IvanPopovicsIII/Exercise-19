package com.example.exercise_19.config;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	// Inject file path from the application properties file
    @Value("${output.filepath}")
    private String filePath;
	
    @Bean
    FileWriter fileWriter() throws IOException {
        return new FileWriter(filePath);
    }
    
}
