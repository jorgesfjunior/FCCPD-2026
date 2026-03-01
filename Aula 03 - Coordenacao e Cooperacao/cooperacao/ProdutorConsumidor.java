import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class ProdutorConsumidor {

    static BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {

        Runnable produtor = () -> {
            try {
                while (true) {
                    int valor = ThreadLocalRandom.current().nextInt(100);
                    buffer.put(valor); // bloqueia se estiver cheio
                    System.out.println("Produzido: " + valor);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumidor = () -> {
            try {
                while (true) {
                    int valor = buffer.take(); // bloqueia se estiver vazio
                    System.out.println("Consumido: " + valor);
                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(produtor).start();
        new Thread(produtor).start();

        new Thread(consumidor).start();
        new Thread(consumidor).start();
    }
}