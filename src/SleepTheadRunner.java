
public class SleepTheadRunner  {

    public static void main(String[] args) {
        SleepThread task1 = new SleepThread("Thread 1");
        SleepThread task2 = new SleepThread("Thread 2");
        SleepThread task3 = new SleepThread("Thread 3");

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
