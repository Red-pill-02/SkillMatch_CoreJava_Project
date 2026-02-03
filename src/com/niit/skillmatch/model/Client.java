package com.niit.skillmatch.model;


public class Client extends User {


    public Client(int id, String name) {
        super(id, name);
    }


    @Override
    public void showMenu() {
        System.out.println("1. Create Task");
        System.out.println("2. View Applicants");
        System.out.println("3. Assign Freelancer");
    }
}