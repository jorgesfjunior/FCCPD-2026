import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProgramaPai {

    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "ProgramaFilho");
            Process processo = pb.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(processo.getInputStream())
            );

            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println("Filho disse: " + linha);
            }

            processo.waitFor();
            System.out.println("Processo filho terminou.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}