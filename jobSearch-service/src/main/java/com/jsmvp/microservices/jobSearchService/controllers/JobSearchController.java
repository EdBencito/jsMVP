package com.jsmvp.microservices.jobSearchService.controllers;

import com.jsmvp.microservices.jobSearchService.dtos.Job;
import com.jsmvp.microservices.jobSearchService.dtos.JobSearchResults;
import com.jsmvp.microservices.jobSearchService.dtos.Message;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RestController
public class JobSearchController {
    private SimpMessagingTemplate messagingTemplate;

    public JobSearchController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    final String AUTH = "4110977c-a97c-4268-a3f0-217766c2f238";

    StringBuilder test_url = new StringBuilder("https://www.reed.co.uk/api/1.0/search?keywords=software engineer");
    StringBuilder url;

    @MessageMapping("/JobSearch")
    public void getJobSearchResultsViaKeywords(Message message) {
        url = new StringBuilder("https://www.reed.co.uk/api/1.0/search?keywords=");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(AUTH, "");
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<JobSearchResults> result = restTemplate.exchange(url.append(message.getMessage()).toString(),
                HttpMethod.GET, entity, JobSearchResults.class);
        JobSearchResults jobSearchResults = result.getBody();

        List<Job> jobs = jobSearchResults.getResults();

        messagingTemplate.convertAndSend("/topic/jobSearchResults", jobs);
        System.out.println("***Search was a success***"); // TODO: REMEMBER TO TAKE OFF

    }

    private static void printJobs(List<Job> jobs) {
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
            System.out.println("Job URL: " + job.getJobUrl() + "\n");
        }
    }

    @GetMapping("/T0") // deserializes the list of jobs from the array of results and prints in console
    public void getJobSearchResultsViaTestURL() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(AUTH, "");
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<JobSearchResults> result = restTemplate.exchange(test_url.toString(), HttpMethod.GET, entity, JobSearchResults.class);
        JobSearchResults jobSearchResults = result.getBody();

        List<Job> jobs = jobSearchResults.getResults(); // TODO: ADDRESS POTENTIAL NULLPOINTEREXCEPTION

        printJobs(jobs);
        System.out.println("***T0 COMPLETE***");
    }

    @GetMapping("/T1")
    // returns jobs json objects like the REED API ******************* NOT REALTIME DISPLAY ON WEBSITE
    public List<Job> getJobSearchResultsViaTestURL2() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(AUTH, "");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<JobSearchResults> result = restTemplate.exchange(test_url.toString(), HttpMethod.GET, entity, JobSearchResults.class);
        JobSearchResults jobSearchResults = result.getBody();

        List<Job> jobs = jobSearchResults.getResults(); // TODO: ADDRESS POTENTIAL NULLPOINTEREXCEPTION

        System.out.println("***T1 COMPLETE***"); // TODO: REMEMBER TO TAKE OFF
        return jobs;

    }

    @GetMapping("/T2") // deserializes the list of jobs from the array of results
    public void getJobSearchResultsViaKeywordsConsole(String input) {
        url = new StringBuilder("https://www.reed.co.uk/api/1.0/search?keywords=");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(AUTH, "");
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<JobSearchResults> result = restTemplate.exchange(url.append(input).toString(), HttpMethod.GET, entity, JobSearchResults.class);
        JobSearchResults jobSearchResults = result.getBody();

        List<Job> jobs = jobSearchResults.getResults(); // TODO: ADDRESS POTENTIAL NULLPOINTEREXCEPTION

        printJobs(jobs);
        System.out.println("***T2 COMPLETE***");
    }
}
