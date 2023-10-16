package com.jsmvp.microservices.jobSearchservice.dto;


public class Job {
    private int jobId;
    private int employerId;
    private String employerName;
    private Object employerProfileId;
    private Object employerProfileName;
    private String jobTitle;
    private String locationName;
    private double minimumSalary;
    private double maximumSalary;
    private String currency;
    private String expirationDate;
    private String date;
    private String jobDescription;
    private int applications;
    private String jobUrl;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public Object getEmployerProfileId() {
        return employerProfileId;
    }

    public void setEmployerProfileId(Object employerProfileId) {
        this.employerProfileId = employerProfileId;
    }

    public Object getEmployerProfileName() {
        return employerProfileName;
    }

    public void setEmployerProfileName(Object employerProfileName) {
        this.employerProfileName = employerProfileName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(double minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public double getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(double maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public int getApplications() {
        return applications;
    }

    public void setApplications(int applications) {
        this.applications = applications;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }
}
