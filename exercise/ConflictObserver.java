package exercise;

public class ConflictObserver implements Observer {
    @Override
    public void notify(String message) {
        System.out.println("[Conflict Alert] " + message);
    }
}
