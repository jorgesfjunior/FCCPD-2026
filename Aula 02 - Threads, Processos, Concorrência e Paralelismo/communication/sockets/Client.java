import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        String host = "localhost";
        int porta = 12345;

        try (Socket socket = new Socket(host, porta)) {

            System.out.println("Conectado ao servidor!");

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter saida = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String mensagem;

            while (true) {

                System.out.print("Digite uma mensagem: ");
                mensagem = scanner.nextLine();

                saida.println(mensagem);

                String resposta = entrada.readLine();
                System.out.println(resposta);

                if (mensagem.equalsIgnoreCase("sair")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
