package com.jsmvp.microservices.jobSearchService.dtos;

public class Response {
    private String responseContent;

    public Response(String responseContent) {
        this.responseContent = responseContent;
    }

    public String getResponseContent() {
        return responseContent;
    }
}
