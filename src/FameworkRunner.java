
import java.util.concurrent.ForkJoinPool;

public class FameworkRunner  {
    public static void main(String[] args) {
        int size = 10_000_000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }

        long startTime = System.currentTimeMillis();
        long singleThreadSum = computeSumSingleThread(array);
        long singleThreadTime = System.currentTimeMillis() - startTime;

        System.out.println("Single-threaded sum: " + singleThreadSum);
        System.out.println("Single-threaded time: " + singleThreadTime + " ms");

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Famework task = new Famework(array, 0, array.length);

        startTime = System.currentTimeMillis();
        long forkJoinSum = forkJoinPool.invoke(task);
        long forkJoinTime = System.currentTimeMillis() - startTime;

        System.out.println("Fork-Join sum: " + forkJoinSum);
        System.out.println("Fork-Join time: " + forkJoinTime + " ms");

        double improvement = (double) singleThreadTime / forkJoinTime;
        System.out.printf("Performance improvement: %.2f times faster%n", improvement);
    }

    private static long computeSumSingleThread(int[] array) {
        long sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}
