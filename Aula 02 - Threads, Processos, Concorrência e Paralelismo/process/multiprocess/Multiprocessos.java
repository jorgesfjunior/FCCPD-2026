import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Multiprocessos {

    public static void main(String[] args) throws Exception {

        int quantidade = 3; // número de processos paralelos
        List<Process> processos = new ArrayList<>();

        for (int i = 1; i <= quantidade; i++) {
            ProcessBuilder pb = new ProcessBuilder("java", "TrabalhoPesado", String.valueOf(i));
            pb.inheritIO(); // mostra saída direto no console
            Process p = pb.start();
            processos.add(p);
        }

        // Espera todos terminarem
        for (Process p : processos) {
            p.waitFor();
        }

        System.out.println("Todos os processos finalizaram.");
    }
}