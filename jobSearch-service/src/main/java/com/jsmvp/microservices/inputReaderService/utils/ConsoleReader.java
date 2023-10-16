package com.jsmvp.microservices.inputReaderService.utils;

import com.jsmvp.microservices.inputReaderService.services.InputReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleReader {
    private final TaskExecutor taskExecutor;
    private final InputReaderService inputReaderService;

    @Autowired
    public ConsoleReader(TaskExecutor taskExecutor, InputReaderService inputReaderService) {
        this.taskExecutor = taskExecutor;
        this.inputReaderService = inputReaderService;
    }

    public void readFromConsole() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Search: ");
                String input = scanner.nextLine();


//                TODO PROCESS THE INPUT
                inputReaderService.processInput();

            } catch (Exception e) {
                System.err.println("ERRRR" + e.getMessage());
                taskExecutor.execute(this::readFromConsole);
            }
        }

    }
}
