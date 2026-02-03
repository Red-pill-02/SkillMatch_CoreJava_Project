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
        System.out.println("Task created successfully");
    }

    public void viewTasks() {
        for (Task t : tasks) {
            System.out.println(
                    t.getTaskId() + " | " + t.getTitle() + " | " + t.getStatus()
            );
        }
    }

    public void updateTask(int taskId, String newTitle)
            throws TaskClosedException {

        Task t = findTask(taskId);
        if (t.getStatus().equals("ASSIGNED")) {
            throw new TaskClosedException("Cannot edit closed task");
        }
        t.setTitle(newTitle);
    }

    public void assignFreelancer(int taskId)
            throws NoApplicantException {

        Task t = findTask(taskId);
        if (t.getApplicants().isEmpty()) {
            throw new NoApplicantException("No applicants for task");
        }
        t.setStatus("ASSIGNED");
    }

    // FREELANCER
    public void applyForTask(int taskId, Freelancer f)
            throws DuplicateApplicationException, TaskClosedException {

        Task t = findTask(taskId);

        if (!t.getStatus().equals("OPEN")) {
            throw new TaskClosedException("Task already closed");
        }
        if (f.hasApplied(taskId)) {
            throw new DuplicateApplicationException("Already applied");
        }

        f.addAppliedTask(taskId);
        t.getApplicants().add(f);
    }

    public void searchTask(String keyword) {
        for (Task t : tasks) {
            if (t.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(t.getTaskId() + " | " + t.getTitle());
            }
        }
    }

    private Task findTask(int taskId) {
        for (Task t : tasks) {
            if (t.getTaskId() == taskId) return t;
        }
        return null;
    }
}
