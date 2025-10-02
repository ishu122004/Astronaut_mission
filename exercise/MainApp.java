package exercise;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(new ConflictObserver());

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Astronaut Daily Schedule Organizer ---");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. Edit Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. View Tasks by Priority");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter description: ");
                        String desc = sc.nextLine();
                        System.out.print("Enter start time (HH:mm): ");
                        String start = sc.nextLine();
                        System.out.print("Enter end time (HH:mm): ");
                        String end = sc.nextLine();
                        System.out.print("Enter priority (High/Medium/Low): ");
                        String priority = sc.nextLine();

                        Task task = TaskFactory.createTask(desc, start, end, priority);
                        manager.addTask(task);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter task description to remove: ");
                    manager.removeTask(sc.nextLine());
                    break;

                case 3:
                    manager.viewTasks();
                    break;

                case 4:
                    System.out.print("Enter description of task to edit: ");
                    String oldDesc = sc.nextLine();
                    System.out.print("New description (leave empty to keep same): ");
                    String newDesc = sc.nextLine();
                    System.out.print("New start time (HH:mm or empty): ");
                    String newStart = sc.nextLine();
                    System.out.print("New end time (HH:mm or empty): ");
                    String newEnd = sc.nextLine();
                    System.out.print("New priority (High/Medium/Low or empty): ");
                    String newPriority = sc.nextLine();
                    manager.editTask(oldDesc, newDesc, newStart, newEnd, newPriority);
                    break;

                case 5:
                    System.out.print("Enter description of task to mark completed: ");
                    manager.markTaskCompleted(sc.nextLine());
                    break;

                case 6:
                    System.out.print("Enter priority to filter (High/Medium/Low): ");
                    manager.viewTasksByPriority(sc.nextLine());
                    break;

                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}


