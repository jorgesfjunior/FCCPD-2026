public class Livelock {

    static class Recurso {
        private boolean disponivel = true;

        public synchronized boolean usar() {
            if (disponivel) {
                disponivel = false;
                return true;
            }
            return false;
        }

        public synchronized void liberar() {
            disponivel = true;
        }
    }

    public static void main(String[] args) {

        Recurso recurso = new Recurso();

        Thread t1 = new Thread(() -> {
            while (true) {
                if (recurso.usar()) {
                    System.out.println("Thread 1 usando recurso");
                    recurso.liberar();
                    break;
                } else {
                    System.out.println("Thread 1 cedendo...");
                }
            }
        });

        Thread t2 = new Thread(() -> {
                        while (true) {
                if (recurso.usar()) {
                    System.out.println("Thread 2 usando recurso");
                    recurso.liberar();
                    break;
                } else {
                    System.out.println("Thread 2 cedendo...");
                }
            }
        });

        t1.start();
        t2.start();
    }
}

