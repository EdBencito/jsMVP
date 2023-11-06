package com.jsmvp.microservices.jobSearchService.dtos;

public class JobSearchRequest {

    private String message;
    private String location;
    private String distance;

    public JobSearchRequest() {
    }


    public JobSearchRequest(String message, String location, String distance) {
        this.message = message;
        this.location = location;
        this.distance = distance;
    }

    public String getMessage() {
        return message;
    }

    public String getLocation() {
        return location;
    }

    public String getDistance() {
        return distance;
    }
}
