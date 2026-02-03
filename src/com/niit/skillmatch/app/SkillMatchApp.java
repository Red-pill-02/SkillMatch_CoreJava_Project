package com.niit.skillmatch.app;

import java.util.*;
import com.niit.skillmatch.model.*;
import com.niit.skillmatch.service.*;
import com.niit.skillmatch.exception.*;

public class SkillMatchApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskService service = new TaskService();

        Freelancer freelancer = new Freelancer(1, "Freelancer");

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Client");
            System.out.println("2. Freelancer");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("1. Create Task");
                    System.out.println("2. View Tasks");
                    System.out.println("3. Update Task");
                    System.out.println("4. Assign Freelancer");

                    int c = sc.nextInt(); sc.nextLine();

                    try {
                        if (c == 1) {
                            System.out.print("Title: ");
                            service.createTask(sc.nextLine());
                        } else if (c == 2) {
                            service.viewTasks();
                        } else if (c == 3) {
                            System.out.print("Task ID: ");
                            int id = sc.nextInt(); sc.nextLine();
                            System.out.print("New Title: ");
                            service.updateTask(id, sc.nextLine());
                        } else if (c == 4) {
                            System.out.print("Task ID: ");
                            service.assignFreelancer(sc.nextInt());
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    freelancer.showMenu();
                    int f = sc.nextInt(); sc.nextLine();

                    try {
                        if (f == 1) service.viewTasks();
                        else if (f == 2) {
                            System.out.print("Keyword: ");
                            service.searchTask(sc.nextLine());
                        }
                        else if (f == 3) {
                            System.out.print("Task ID: ");
                            service.applyForTask(sc.nextInt(), freelancer);
                        }
                        else if (f == 4) {
                            System.out.println(freelancer.getAppliedTasks());
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}
