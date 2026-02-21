package com.niit.skillmatch.service;

import java.util.*;
import com.niit.skillmatch.model.*;
import com.niit.skillmatch.exception.*;

public class TaskService {

    private List<Task> tasks = new ArrayList<>();
    private int taskCounter = 1;

    // CLIENT
    public void createTask(String title) {
        tasks.add(new Task(taskCounter++, title));
        System.out.println("✅ Task created successfully.");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("ℹ️ No tasks available right now.");
            return;
        }

        System.out.println("\n--- TASK LIST ---");
        for (Task t : tasks) {
            System.out.println(t.getTaskId() + " | " + t.getTitle() + " | Status: " + t.getStatus());
        }
    }

    public void updateTask(int taskId, String newTitle) throws TaskClosedException {
        Task t = findTask(taskId);

        if (t.getStatus().equals("ASSIGNED") || t.getStatus().equals("CLOSED")) {
            throw new TaskClosedException("Cannot edit an assigned/closed task.");
        }

        t.setTitle(newTitle);
        System.out.println("✅ Task updated successfully.");
    }

    public void assignFreelancer(int taskId) throws NoApplicantException {
        Task t = findTask(taskId);

        if (t.getApplicants().isEmpty()) {
            throw new NoApplicantException("No applicants for this task yet.");
        }

        if (t.getStatus().equals("ASSIGNED")) {
            System.out.println("ℹ️ Task is already assigned.");
            return;
        }

        t.setStatus("ASSIGNED");
        System.out.println("✅ Task assigned successfully to the first applicant.");
    }

    public void changeTaskStatus(int taskId, String status) {
        Task t = findTask(taskId);

        String normalizedStatus = status.trim().toUpperCase();
        if (!normalizedStatus.equals("OPEN") && !normalizedStatus.equals("ASSIGNED") && !normalizedStatus.equals("CLOSED")) {
            System.out.println("❌ Invalid status. Allowed values: OPEN, ASSIGNED, CLOSED");
            return;
        }

        t.setStatus(normalizedStatus);
        System.out.println("✅ Task status updated to: " + normalizedStatus);
    }

    // FREELANCER
    public void applyForTask(int taskId, Freelancer f) throws DuplicateApplicationException, TaskClosedException {
        Task t = findTask(taskId);

        if (!t.getStatus().equals("OPEN")) {
            throw new TaskClosedException("Task is not open for applications.");
        }

        if (f.hasApplied(taskId)) {
            throw new DuplicateApplicationException("You have already applied for this task.");
        }

        f.addAppliedTask(taskId);
        t.getApplicants().add(f);
        System.out.println("✅ Applied for task successfully.");
    }

    public void searchTask(String keyword) {
        boolean found = false;

        for (Task t : tasks) {
            if (t.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(t.getTaskId() + " | " + t.getTitle() + " | Status: " + t.getStatus());
                found = true;
            }
        }

        if (!found) {
            System.out.println("ℹ️ No tasks matched your search.");
        }
    }

    private Task findTask(int taskId) {
        for (Task t : tasks) {
            if (t.getTaskId() == taskId) {
                return t;
            }
        }
        throw new IllegalArgumentException("❌ Task with ID " + taskId + " not found.");
    }
}
