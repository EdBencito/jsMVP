package com.jsmvp.microservices.jobSearchService;

import com.jsmvp.microservices.inputReaderService.configurations.TaskExecutorConfig;
import com.jsmvp.microservices.inputReaderService.services.InputReaderService;
import com.jsmvp.microservices.jobSearchService.controllers.JobSearchController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Import(TaskExecutorConfig.class)
@ExtendWith(MockitoExtension.class)
public class InputReaderServiceTests {

    @Autowired
    private InputReaderService inputReaderService;

    @MockBean
    private JobSearchController jobSearchController; // Use the injected bean

    @Test
    void shouldPassWhenInputIsValid() {
        // Call the method you want to test
        inputReaderService.processInput("testInput");

        // Verify the interaction with the injected mock object
        verify(jobSearchController, times(1)).getJobSearchResultsViaKeywords2("testInput");
    }
}

