package com.niit.skillmatch.model;

import java.util.*;

public class Freelancer extends User {
    private List<Integer> appliedTasks = new ArrayList<>();

    public Freelancer(int id, String name) {
        super(id, name);
    }

    public boolean hasApplied(int taskId) {
        return appliedTasks.contains(taskId);
    }

    public void addAppliedTask(int taskId) {
        appliedTasks.add(taskId);
    }

    public List<Integer> getAppliedTasks() {
        return appliedTasks;
    }

    @Override
    public void showMenu() {
        System.out.println("1. View Tasks");
        System.out.println("2. Search Tasks");
        System.out.println("3. Apply for Task");
        System.out.println("4. View Applied Tasks");
        System.out.println("5. Back");
    }
}
