
public class NamingThreadRunner {

    public static void main(String[] args) {

        NamingThread task1 = new NamingThread();
        NamingThread task2 = new NamingThread();
        NamingThread task3 = new NamingThread();

        Thread thread1 = new Thread(task1, "Thread-1");
        Thread thread2 = new Thread(task2, "Thread-2");
        Thread thread3 = new Thread(task3, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
