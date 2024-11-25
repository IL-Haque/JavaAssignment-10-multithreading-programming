
class NamingThread implements Runnable {

    public NamingThread() {

    }
    @Override
    public void run() {
        System.out.println("Current thread name: " + Thread.currentThread().getName());
    }
}
