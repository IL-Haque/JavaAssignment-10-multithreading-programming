
import java.util.concurrent.TimeUnit;

public class ThreadPool implements Runnable {
    private final int taskId;

    public ThreadPool(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskId + " is starting on thread: " + Thread.currentThread().getName());
        try {
            // Simulate task execution time
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Task " + taskId + " was interrupted.");
        }
        System.out.println("Task " + taskId + " has completed.");
    }
}
