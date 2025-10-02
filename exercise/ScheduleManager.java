package exercise;

import java.util.*;

public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> tasks;
    private final List<Observer> observers;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (Observer o : observers) {
            o.notify(message);
        }
    }

    // --- Core Features ---
    public void addTask(Task task) {
        for (Task t : tasks) {
            boolean overlap = !(task.getEndTime().isBefore(t.getStartTime()) ||
                                task.getStartTime().isAfter(t.getEndTime()));
            if (overlap) {
                notifyObservers("Task conflicts with existing task \"" + t.getDescription() + "\".");
                return;
            }
        }
        tasks.add(task);
        System.out.println("Task added successfully. No conflicts.");
    }

    public void removeTask(String description) {
        Task found = findTaskByDescription(description);
        if (found != null) {
            tasks.remove(found);
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Error: Task not found.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        tasks.sort(Comparator.comparing(Task::getStartTime));
        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    // --- Optional Features ---
    public void editTask(String description, String newDesc, String newStart, String newEnd, String newPriority) {
        Task found = findTaskByDescription(description);
        if (found == null) {
            System.out.println("Error: Task not found.");
            return;
        }

        try {
            Task updated = TaskFactory.createTask(
                    newDesc.isEmpty() ? found.getDescription() : newDesc,
                    newStart.isEmpty() ? found.getStartTime().toString() : newStart,
                    newEnd.isEmpty() ? found.getEndTime().toString() : newEnd,
                    newPriority.isEmpty() ? found.getPriority() : newPriority
            );
            tasks.remove(found);
            addTask(updated);
            System.out.println("Task updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating task: " + e.getMessage());
        }
    }

    public void markTaskCompleted(String description) {
        Task found = findTaskByDescription(description);
        if (found != null) {
            found.markCompleted();
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Error: Task not found.");
        }
    }

    public void viewTasksByPriority(String priority) {
        boolean found = false;
        for (Task t : tasks) {
            if (t.getPriority().equalsIgnoreCase(priority)) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found for priority: " + priority);
        }
    }

    // --- Helper ---
    private Task findTaskByDescription(String description) {
        for (Task t : tasks) {
            if (t.getDescription().equalsIgnoreCase(description)) {
                return t;
            }
        }
        return null;
    }
}



