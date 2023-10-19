package com.jsmvp.microservices.inputReaderService.services;

import com.jsmvp.microservices.jobSearchService.controllers.JobSearchController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class InputReaderService {
    private int currentAttempt;
    private final int maxRetryAttempts = 3;

    private final TaskExecutor taskExecutor;
    @Autowired
    JobSearchController jobSearchController;

    @Autowired
    public InputReaderService(@Qualifier("customTaskExecutor") TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Async("customTaskExecutor")
    public void processInput(String input) {
        while (currentAttempt < maxRetryAttempts) {
            try {
                System.out.println("Input Received in InputReaderService: " + input);
//                TODO process ConsoleReader website textbox inputs can be done directly from the controller not sure if its a good idea thoughTFD

//                jobSearchController.getJobSearchResultsViaKeywords6_5(input);
                jobSearchController.getJobSearchResultsViaKeywords6(input);
                System.out.println("InputReaderService Finished");

                break;
            } catch (Exception e) {
                System.err.println("ERROR in InputReaderService: " + e.getMessage());

                currentAttempt++;
            }
        }

        if (currentAttempt >= maxRetryAttempts) {
            System.err.println("Input processing failed after max retry attempts");
            taskExecutor.execute(() -> processInput(input));
        }
    }
}
