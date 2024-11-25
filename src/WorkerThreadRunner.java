
public class WorkerThreadRunner {
    public static void main(String[] args) {
        CustomThreadPool threadPool = new CustomThreadPool(3, 6);

        for (int i = 1; i <= 15; i++) {
            final int taskID = i;
            threadPool.submitTask(() -> {
                System.out.println("Task " + taskID + " is running on thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        try {
            Thread.sleep(5000);
            System.out.println("Resizing thread pool...");
            threadPool.resizePool(2, 4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        threadPool.shutdown();
        System.out.println("Thread pool shutdown.");
    }
}
