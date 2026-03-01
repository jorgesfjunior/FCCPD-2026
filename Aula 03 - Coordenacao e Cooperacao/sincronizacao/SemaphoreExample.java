import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    static int counter = 0;
    static Semaphore semaphore = new Semaphore(1); // 1 = exclusão mútua

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            for (int i = 0; i < 100000; i++) {
                try {
                    semaphore.acquire(); // solicita permissão
                    counter++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // libera permissão
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