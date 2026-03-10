public class ContadorLock {

    static int contador = 0;

    public synchronized static void incrementar() {
        contador++;
    }

    public static void main(String[] args) throws Exception {

        long inicio = System.currentTimeMillis();

        Thread[] threads = new Thread[200];

        for (int i = 0; i < 200; i++) {

            threads[i] = new Thread(() -> {

                for (int j = 0; j < 1000000; j++) {
                    incrementar();
                }

            });

            threads[i].start();
        }

        for (Thread t : threads)
            t.join();

        long fim = System.currentTimeMillis();

        System.out.println("Contador: " + contador);
        System.out.println("Tempo: " + (fim - inicio) + " ms");
    }
}