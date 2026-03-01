public class JoinExample {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Thread finalizada.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t1.join(); // espera t1 terminar

        System.out.println("Main continua após join.");
    }
}