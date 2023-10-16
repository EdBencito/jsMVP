package com.jsmvp.microservices.jobSearchService.controllers;

import com.jsmvp.microservices.jobSearchService.dtos.Job;
import com.jsmvp.microservices.jobSearchService.dtos.JobSearchResults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class jobSearchController {
//    private final InputReaderService inputReaderService; // TODO: setup for  injecting inputreaderserivce to be used in this controller
//    @Autowired
//    public jobSearchController(InputReaderService inputReaderService){
//        this.inputReaderService = inputReaderService;
//    }


    String AUTH = "4110977c-a97c-4268-a3f0-217766c2f238";

    StringBuilder kurl = new StringBuilder("https://www.reed.co.uk/api/1.0/search?keywords=software engineer");

    @GetMapping("/t0")
    public void t0() {
        System.out.println("***TESTING TESTING 123***");
    }

    @GetMapping("/t4") // deserializes the list of jobs from the array of results
    public void getJobSearchResultsViaKeywords4() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(AUTH, "");
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<JobSearchResults> result = restTemplate.exchange(kurl.toString(), HttpMethod.GET, entity, JobSearchResults.class);
        JobSearchResults jobSearchResults = result.getBody();

        List<Job> jobs = jobSearchResults.getResults(); // TODO: ADDRESS POTENTIAL NULLPOINTEREXCEPTION

        for (Job job : jobs) {
            System.out.println("Job ID: " + job.getJobId());
            System.out.println("Employer Name: " + job.getEmployerName());
            System.out.println("Job Title: " + job.getJobTitle());
            System.out.println("Location: " + job.getLocationName());
            System.out.println("Minimum Salary: " + job.getMinimumSalary() + " " + job.getCurrency());
            System.out.println("Maximum Salary: " + job.getMaximumSalary() + " " + job.getCurrency());
            System.out.println("Expiration Date: " + job.getExpirationDate());
            System.out.println("Date: " + job.getDate());
            System.out.println("Job Description: " + job.getJobDescription());
            System.out.println("Applications: " + job.getApplications());
            System.out.println("Job URL: " + job.getJobUrl());
            System.out.println(); // Add a newline for separation
            System.out.println("***T4 SUCCESS***"); // TODO: REMEMBER TO TAKE OFF

        }
    }

    @GetMapping("/t5") // returns jobs json objects like the REED API
    public List<Job> getJobSearchResultsViaKeywords5() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(AUTH, "");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<JobSearchResults> result = restTemplate.exchange(kurl.toString(), HttpMethod.GET, entity, JobSearchResults.class);
        JobSearchResults jobSearchResults = result.getBody();

        List<Job> jobs = jobSearchResults.getResults(); // TODO: ADDRESS POTENTIAL NULLPOINTEREXCEPTION

        System.out.println("***T5 SUCCESS***"); // TODO: REMEMBER TO TAKE OFF
        return jobs;

    }
}
