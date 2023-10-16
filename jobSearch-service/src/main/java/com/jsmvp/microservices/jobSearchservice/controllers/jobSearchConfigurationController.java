package com.jsmvp.microservices.jobSearchservice.controllers;

import com.jsmvp.microservices.jobSearchservice.Configuration;
import com.jsmvp.microservices.jobSearchservice.bean.JobSearchConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class jobSearchConfigurationController {
    @Autowired
    private Configuration configuration;

    @GetMapping("/jobSearch")
    public JobSearchConfiguration retreiveJobSearchFromConfigurations() {
        return new JobSearchConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
}
