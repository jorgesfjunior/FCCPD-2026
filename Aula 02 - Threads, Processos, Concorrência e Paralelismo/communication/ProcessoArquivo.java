import java.io.*;

public class ProcessoArquivo {

    static class Escritor implements Runnable {

        private String arquivo;

        public Escritor(String arquivo) {
            this.arquivo = arquivo;
        }

        @Override
        public void run() {
            try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo, true))) {

                for (int i = 1; i <= 10; i++) {
                    String mensagem = "Linha " + i;
                    writer.println(mensagem);
                    writer.flush(); // força escrita imediata

                    System.out.println("Escreveu: " + mensagem);
                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Leitor implements Runnable {

        private String arquivo;

        public Leitor(String arquivo) {
            this.arquivo = arquivo;
        }

        @Override
        public void run() {
            try {
                File file = new File(arquivo);
                long ultimaPosicao = 0;

                while (true) {

                    long tamanhoAtual = file.length();

                    if (tamanhoAtual > ultimaPosicao) {

                        RandomAccessFile raf = new RandomAccessFile(file, "r");
                        raf.seek(ultimaPosicao);

                        String linha;
                        while ((linha = raf.readLine()) != null) {
                            System.out.println("Leu: " + linha);
                        }

                        ultimaPosicao = raf.getFilePointer();
                        raf.close();
                    }

                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        String arquivo = "dados.txt";

        Thread escritor = new Thread(new Escritor(arquivo));
        Thread leitor = new Thread(new Leitor(arquivo));

        leitor.start();   // começa lendo
        escritor.start(); // começa escrevendo
    }
}

