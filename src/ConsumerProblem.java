
import java.util.LinkedList;
import java.util.Queue;

public class ConsumerProblem {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity;

    public ConsumerProblem(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (buffer.size() == capacity) {
            System.out.println("Buffer is full. Producer is waiting...");
            wait();
        }
        buffer.add(item);
        System.out.println("Produced: " + item);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Buffer is empty. Consumer is waiting...");
            wait();
        }
        int item = buffer.poll();
        System.out.println("Consumed: " + item);
        notify(); // Notify the producer that space is available
        return item;
    }
}
