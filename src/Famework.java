
import java.util.concurrent.RecursiveTask;

public class Famework extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10;
    private final int[] array;
    private final int start;
    private final int end;

    public Famework(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {

            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            Famework leftTask = new Famework(array, start, mid);
            Famework rightTask = new Famework(array, mid, end);

            leftTask.fork();
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}
