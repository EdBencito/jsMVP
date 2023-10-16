package com.jsmvp.microservices.inputReaderService.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class InputReaderService {

    @Async
    public void processInput() {
//        TODO process ConsoleReader website textbox inputs can be done directly from the controller not sure if its a good idea though

    }
}
