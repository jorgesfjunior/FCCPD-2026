import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    static int counter = 0;
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            for (int i = 0; i < 100000; i++) {
                lock.lock();       // entra na região crítica
                try {
                    counter++;
                } finally {
                    lock.unlock(); // garante liberação do lock
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Valor final: " + counter);
    }
}