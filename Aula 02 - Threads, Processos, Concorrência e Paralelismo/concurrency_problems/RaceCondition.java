public class RaceCondition{

    static int contador = 0; // variável compartilhada

    static class Incrementador implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                contador++; // operação NÃO atômica
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Incrementador());
        Thread t2 = new Thread(new Incrementador());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Valor final do contador: " + contador);
    }
}
