package com.jsmvp.microservices.inputReaderService.utils;

import com.jsmvp.microservices.inputReaderService.services.InputReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleReader {
    private final InputReaderService inputReaderService;

    @Autowired
    public ConsoleReader(InputReaderService inputReaderService) {
        this.inputReaderService = inputReaderService;
    }

    public void readFromConsole() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Search: ");
                String input = scanner.nextLine();
                inputReaderService.processInput(input);
            } catch (Exception e) {
                System.err.println("ERROR in ConsoleReader " + e.getMessage());
            }
        }
    }
}
