package exercise;

public class TaskFactory {
    public static Task createTask(String description, String start, String end, String priority) {
        return new Task(description, start, end, priority);
    }
}
