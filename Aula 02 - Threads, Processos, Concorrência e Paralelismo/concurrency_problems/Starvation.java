public class Starvation {

    static class Trabalhador implements Runnable {
        private String nome;

        public Trabalhador(String nome) {
            this.nome = nome;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(nome + " executando...");
            }
        }
    }

    public static void main(String[] args) {

        Thread alta1 = new Thread(new Trabalhador("Alta Prioridade 1"));
        Thread alta2 = new Thread(new Trabalhador("Alta Prioridade 2"));
        Thread baixa = new Thread(new Trabalhador("Baixa Prioridade"));

        alta1.setPriority(Thread.MAX_PRIORITY);
        alta2.setPriority(Thread.MAX_PRIORITY);
        baixa.setPriority(Thread.MIN_PRIORITY);

        alta1.start();
        alta2.start();
        baixa.start();
    }
}
