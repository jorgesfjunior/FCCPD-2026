import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Processo {

    public static void main(String[] args) {
        try {
            // Criando o processo (Windows usa "cmd /c", Linux/Mac usa "bash -c")
            ProcessBuilder pb = new ProcessBuilder("ping", "google.com");
            
            Process processo = pb.start();

            // Lendo a saída do processo
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(processo.getInputStream())
            );

            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }

            int codigoSaida = processo.waitFor();
            System.out.println("Processo finalizado com código: " + codigoSaida);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}