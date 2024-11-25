
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner {
    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // Submit multiple tasks to the thread pool
        for (int i = 1; i <= 10; i++) {
            threadPool.submit(new ThreadPool(i));
        }

        // Shutdown the thread pool
        threadPool.shutdown();
        try {
            // Wait for all tasks to finish or timeout after 10 seconds
            if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Forcing shutdown as tasks did not complete in time.");
                threadPool.shutdownNow(); // Force shutdown if tasks are not finished
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks completed. Thread pool is shut down.");
    }
}
