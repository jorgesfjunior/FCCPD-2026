import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class ProdutorConsumidor {

    // Classe Produtor
    static class Produtor implements Runnable {
        private BlockingQueue<String> fila;

        public Produtor(BlockingQueue<String> fila) {
            this.fila = fila;
        }

        @Override
        public void run() {
            try {
                fila.put("Mensagem do produtor");
                System.out.println("Produtor enviou a mensagem");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Classe Consumidor
    static class Consumidor implements Runnable {
        private BlockingQueue<String> fila;

        public Consumidor(BlockingQueue<String> fila) {
            this.fila = fila;
        }

        @Override
        public void run() {
            try {
                String msg = fila.take();
                System.out.println("Consumidor recebeu: " + msg);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> fila = new ArrayBlockingQueue<>(1);

        Thread produtor = new Thread(new Produtor(fila));
        Thread consumidor = new Thread(new Consumidor(fila));

        produtor.start();
        consumidor.start();

        produtor.join();
        consumidor.join();
    }
}


