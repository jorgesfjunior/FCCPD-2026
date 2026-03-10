public class MuitasThreads {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
            }).start();

        }
    }
}