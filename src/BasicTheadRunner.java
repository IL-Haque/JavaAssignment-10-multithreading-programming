
public class BasicTheadRunner {
    public static void main(String[] args) {

        BasicThead myRunnable = new BasicThead();

        Thread thread = new Thread(myRunnable);

        thread.start();
    }
}
