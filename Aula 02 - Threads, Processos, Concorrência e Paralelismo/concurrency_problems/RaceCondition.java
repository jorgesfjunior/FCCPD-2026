public class RaceCondition {

    static int counter = 0; // Variável compartilhada

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter++; // operação NÃO atômica
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter++; // operação NÃO atômica
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Valor final do contador: " + counter);
    }
}