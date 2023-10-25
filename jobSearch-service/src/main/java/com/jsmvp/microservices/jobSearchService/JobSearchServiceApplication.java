package com.jsmvp.microservices.jobSearchService;

import com.jsmvp.microservices.inputReaderService.utils.ConsoleReader;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan("com.jsmvp.microservices")
public class JobSearchServiceApplication implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(JobSearchServiceApplication.class, args);

        startConsoleReader();
    }

    private static void startConsoleReader() {
        ConsoleReader consoleReader = applicationContext.getBean(ConsoleReader.class);
        consoleReader.readFromConsole();
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        JobSearchServiceApplication.applicationContext = applicationContext;
    }
}