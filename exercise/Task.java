package exercise;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String priority;
    private boolean completed;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public Task(String description, String start, String end, String priority) {
        this.description = description;
        this.startTime = parseTime(start);
        this.endTime = parseTime(end);
        this.priority = priority;
        this.completed = false;

        if (this.startTime == null || this.endTime == null) {
            throw new IllegalArgumentException("Error: Invalid time format. Use HH:mm (e.g., 07:00).");
        }
        if (!endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("Error: End time must be after start time.");
        }
    }

    private LocalTime parseTime(String timeStr) {
        try {
            return LocalTime.parse(timeStr, FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public String getDescription() { return description; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return completed; }
    public void markCompleted() { this.completed = true; }

    @Override
    public String toString() {
        return String.format("%s - %s: %s [%s] %s",
                startTime.format(FORMATTER),
                endTime.format(FORMATTER),
                description,
                priority,
                (completed ? "(Completed)" : ""));
    }
}

