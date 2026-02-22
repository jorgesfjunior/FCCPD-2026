public class TrabalhoPesado {

    public static void main(String[] args) throws Exception {
        int id = Integer.parseInt(args[0]);

        System.out.println("Processo " + id + " iniciado.");

        for (int i = 1; i <= 5; i++) {
            System.out.println("Processo " + id + " executando passo " + i);
            Thread.sleep(1000); // simula trabalho
        }

        System.out.println("Processo " + id + " finalizado.");
    }
}