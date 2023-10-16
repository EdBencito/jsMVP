package com.jsmvp.microservices.jobSearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Scanner;

@SpringBootApplication
@EnableAsync
public class JobSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobSearchServiceApplication.class, args);

//        readConsole();
    }

//    @Bean
//    public TaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(1);
//        executor.setMaxPoolSize(5);
//        executor.setQueueCapacity(10);
//        executor.setKeepAliveSeconds(10);
//        executor.initialize();
//        return executor;
//    }

//    @Autowired
//    private TaskExecutor taskExecutor;


    @Async
    private static void readConsole() {

        Scanner scn = new Scanner(System.in);
        String s;
        while (!(s = scn.nextLine()).equals("/q")) {
            try {
                System.out.println("What you entered: " + s);
            } catch (Exception e) {
                System.err.println("ERRRRRRRR EXCEPTION");
            }
        }

    }
}