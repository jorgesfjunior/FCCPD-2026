import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 1000; i++) {

            pool.submit(() -> {
                System.out.println(Thread.currentThread().getName());
            });

        }

        pool.shutdown();
    }
}