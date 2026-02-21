package com.niit.skillmatch.app;

import java.util.*;
import com.niit.skillmatch.model.*;
import com.niit.skillmatch.service.*;

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
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    runClientMenu(sc, service);
                    break;

                case 2:
                    runFreelancerMenu(sc, service, freelancer);
                    break;

                case 3:
                    System.out.println("👋 Exiting SkillMatch. Goodbye!");
                    return;

                default:
                    System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    private static void runClientMenu(Scanner sc, TaskService service) {
        while (true) {
            System.out.println("\n--- CLIENT MENU ---");
            System.out.println("1. Create Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task Title");
            System.out.println("4. Assign Freelancer");
            System.out.println("5. Change Task Status");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");

            int c;
            try {
                c = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                continue;
            }

            try {
                if (c == 1) {
                    System.out.print("Enter task title: ");
                    service.createTask(sc.nextLine());
                } else if (c == 2) {
                    service.viewTasks();
                } else if (c == 3) {
                    System.out.print("Enter task ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter new task title: ");
                    service.updateTask(id, sc.nextLine());
                } else if (c == 4) {
                    System.out.print("Enter task ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    service.assignFreelancer(id);
                } else if (c == 5) {
                    System.out.print("Enter task ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter new status (OPEN/ASSIGNED/CLOSED): ");
                    String status = sc.nextLine();
                    service.changeTaskStatus(id, status);
                } else if (c == 6) {
                    System.out.println("↩️ Returning to main menu...");
                    break;
                } else {
                    System.out.println("❌ Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }

    private static void runFreelancerMenu(Scanner sc, TaskService service, Freelancer freelancer) {
        while (true) {
            System.out.println("\n--- FREELANCER MENU ---");
            freelancer.showMenu();
            System.out.print("Choose an option: ");

            int f;
            try {
                f = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                continue;
            }

            try {
                if (f == 1) {
                    service.viewTasks();
                } else if (f == 2) {
                    System.out.print("Enter keyword: ");
                    service.searchTask(sc.nextLine());
                } else if (f == 3) {
                    System.out.print("Enter task ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    service.applyForTask(id, freelancer);
                } else if (f == 4) {
                    if (freelancer.getAppliedTasks().isEmpty()) {
                        System.out.println("ℹ️ You have not applied for any tasks yet.");
                    } else {
                        System.out.println("Applied Task IDs: " + freelancer.getAppliedTasks());
                    }
                } else if (f == 5) {
                    System.out.println("↩️ Returning to main menu...");
                    break;
                } else {
                    System.out.println("❌ Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }
}
