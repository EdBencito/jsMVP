package com.jsmvp.microservices.jobSearchservice.bean;

public class JobSearchConfiguration {
    private int maximum;
    private int minimum;

    protected JobSearchConfiguration() {

    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public JobSearchConfiguration(int maximum, int minimum) {
        super();
        this.maximum = maximum;
        this.minimum = minimum;

    }
}
