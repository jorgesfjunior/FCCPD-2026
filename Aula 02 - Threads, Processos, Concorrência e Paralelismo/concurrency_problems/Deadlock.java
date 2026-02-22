public class Deadlock {

    private static final Object recursoA = new Object();
    private static final Object recursoB = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            synchronized (recursoA) {
                System.out.println("Thread 1 bloqueou Recurso A");

                try { Thread.sleep(100); } 
                catch (InterruptedException e) {}

                System.out.println("Thread 1 esperando Recurso B...");

                synchronized (recursoB) {
                    System.out.println("Thread 1 bloqueou Recurso B");
                }
            }
        });

        Thread t2 = new Thread(() -> {

            synchronized (recursoB) {
                System.out.println("Thread 2 bloqueou Recurso B");

                try { Thread.sleep(100); } 
                catch (InterruptedException e) {}

                System.out.println("Thread 2 esperando Recurso A...");

                synchronized (recursoA) {
                    System.out.println("Thread 2 bloqueou Recurso A");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
