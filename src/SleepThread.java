
class SleepThread implements Runnable {

    private String threadName;

    public SleepThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            System.out.println(threadName + " - Started");

            Thread.sleep(2000);

            System.out.println(threadName + " - Finished after sleep");

        } catch (InterruptedException e) {
            System.out.println(threadName + " was interrupted");
        }
    }
}
