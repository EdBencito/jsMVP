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
                jobSearchController.getJobSearchResultsViaKeywordsConsole(input);
                break;
            } catch (RuntimeException e) {
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
