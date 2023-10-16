package com.jsmvp.microservices.jobSearchservice;

import java.util.List;

public class JobSearchResults {
    private List<Job> results;

    public List<Job> getResults() {
        return results;
    }

    public void setResults(List<Job> results) {
        this.results = results;
    }
}
