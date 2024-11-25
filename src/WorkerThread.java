import java.util.concurrent.BlockingQueue;

class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> taskQueue;
    private volatile boolean isStopped = false;

    public WorkerThread(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (!isStopped || !taskQueue.isEmpty()) {
            try {
                Runnable task = taskQueue.poll(); // Fetch task
                if (task != null) {
                    task.run();
                }
            } catch (Exception e) {
                System.out.println("Task execution error: " + e.getMessage());
            }
        }
    }

    public void stopWorker() {
        isStopped = true;
        this.interrupt(); // Interrupt in case the thread is waiting
    }
}
