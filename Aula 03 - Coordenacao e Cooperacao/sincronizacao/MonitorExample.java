public class MonitorExample {

    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            for (int i = 0; i < 100000; i++) {
                synchronized (MonitorExample.class) {
                    counter++;
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