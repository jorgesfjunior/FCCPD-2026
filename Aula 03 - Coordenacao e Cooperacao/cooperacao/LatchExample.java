import java.util.concurrent.CountDownLatch;

public class LatchExample {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);

        Runnable tarefa = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " trabalhando...");
                Thread.sleep((long)(Math.random() * 3000));
                System.out.println(Thread.currentThread().getName() + " terminou.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown(); // decrementa contador
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(tarefa).start();
        }

        System.out.println("Main aguardando...");
        latch.await(); // espera contador chegar a zero
        System.out.println("Todas as threads terminaram!");
    }
}