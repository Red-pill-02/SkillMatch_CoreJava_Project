package com.niit.skillmatch.model;

import java.util.*;

public class Task {
    private int taskId;
    private String title;
    private String status; // OPEN / ASSIGNED
    private List<Freelancer> applicants = new ArrayList<>();

    public Task(int taskId, String title) {
        this.taskId = taskId;
        this.title = title;
        this.status = "OPEN";
    }

    public int getTaskId() { return taskId; }
    public String getTitle() { return title; }
    public String getStatus() { return status; }
    public List<Freelancer> getApplicants() { return applicants; }

    public void setTitle(String title) { this.title = title; }
    public void setStatus(String status) { this.status = status; }
}
