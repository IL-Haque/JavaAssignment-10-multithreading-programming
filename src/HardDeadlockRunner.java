
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class HardDeadlockRunner {
    public static void main(String[] args) {
        HardDeadlock deadlockScenario = new HardDeadlock();

        Thread thread1 = new Thread(() -> deadlockScenario.method1(), "Thread-1");

        Thread thread2 = new Thread(() -> deadlockScenario.method2(), "Thread-2");

        thread1.start();
        thread2.start();

        Thread monitor = new Thread(() -> {
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            while (true) {
                long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
                if (deadlockedThreads != null) {
                    System.out.println("Deadlock detected! Threads involved:");
                    for (long threadId : deadlockedThreads) {
                        System.out.println(threadMXBean.getThreadInfo(threadId).getThreadName());
                    }
                    System.out.println("Taking corrective action to resolve the deadlock...");

                    thread1.interrupt();
                    thread2.interrupt();
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        monitor.setDaemon(true);
        monitor.start();
    }
}
