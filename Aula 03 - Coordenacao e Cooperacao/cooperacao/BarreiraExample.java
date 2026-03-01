import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarreiraExample {

    static CyclicBarrier barrier = new CyclicBarrier(3, () ->
        System.out.println(">>> Todas as threads chegaram na barreira! <<<")
    );

    public static void main(String[] args) {

        Runnable tarefa = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " executando fase 1");
                Thread.sleep((long)(Math.random() * 2000));

                System.out.println(Thread.currentThread().getName() + " esperando na barreira");
                barrier.await(); // espera todas chegarem

                System.out.println(Thread.currentThread().getName() + " executando fase 2");

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(tarefa).start();
        }
    }
}