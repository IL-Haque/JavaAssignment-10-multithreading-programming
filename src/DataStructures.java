
import java.util.concurrent.*;

class DataStructures {
    private final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public void updateMap(String key, int value) {
        map.merge(key, value, Integer::sum);
    }

    public void printMap() {
        System.out.println("Final Map: " + map);
    }
}

class ProducerConsumerDemo {
    private final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 1; i <= 20; i++) {
                    System.out.println("Produced: " + i);
                    queue.put(i); // Blocking call
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Integer item = queue.take(); // Blocking call
                    System.out.println("Consumed: " + item);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void startProducerConsumer() {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());
        producer.start();
        consumer.start();
    }
}

