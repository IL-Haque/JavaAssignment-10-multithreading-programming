import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

class CustomThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final List<WorkerThread> workers;
    private volatile boolean isStopped = false;
    private int coreThreads;
    private int maxThreads;

    public CustomThreadPool(int coreThreads, int maxThreads) {
        this.coreThreads = coreThreads;
        this.maxThreads = maxThreads;
        taskQueue = new LinkedBlockingQueue<>();
        workers = new ArrayList<>();

        for (int i = 0; i < coreThreads; i++) {
            WorkerThread worker = new WorkerThread(taskQueue);
            workers.add(worker);
            worker.start();
        }
    }

    public synchronized void submitTask(Runnable task) {
        if (isStopped) {
            throw new IllegalStateException("ThreadPool is stopped");
        }
        taskQueue.offer(task);

        // Dynamically resize pool
        if (taskQueue.size() > workers.size() && workers.size() < maxThreads) {
            WorkerThread worker = new WorkerThread(taskQueue);
            workers.add(worker);
            worker.start();
        }
    }

    public synchronized void shutdown() {
        isStopped = true;
        for (WorkerThread worker : workers) {
            worker.stopWorker();
        }
    }

    public synchronized void resizePool(int newCoreThreads, int newMaxThreads) {
        this.coreThreads = newCoreThreads;
        this.maxThreads = newMaxThreads;

        // Reduce threads if new size is smaller
        while (workers.size() > coreThreads) {
            WorkerThread worker = workers.remove(workers.size() - 1);
            worker.stopWorker();
        }

        // Add threads if new size is larger
        while (workers.size() < coreThreads) {
            WorkerThread worker = new WorkerThread(taskQueue);
            workers.add(worker);
            worker.start();
        }
    }
}
