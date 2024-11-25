import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DataStructuresRunner {
    public static void main(String[] args) {

        DataStructures concurrentMapDemo = new DataStructures();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable mapTask1 = () -> concurrentMapDemo.updateMap("A", 10);
        Runnable mapTask2 = () -> concurrentMapDemo.updateMap("B", 20);
        Runnable mapTask3 = () -> concurrentMapDemo.updateMap("A", 15);

        executor.execute(mapTask1);
        executor.execute(mapTask2);
        executor.execute(mapTask3);
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        concurrentMapDemo.printMap();

        ProducerConsumerDemo producerConsumerDemo = new ProducerConsumerDemo();
        producerConsumerDemo.startProducerConsumer();
    }
}
