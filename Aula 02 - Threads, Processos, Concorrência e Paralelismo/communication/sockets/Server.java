import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        int porta = 12345;

        try (ServerSocket serverSocket = new ServerSocket(porta)) {

            System.out.println("Servidor iniciado na porta " + porta);
            System.out.println("Aguardando cliente...");

            Socket socket = serverSocket.accept(); // BLOQUEIA
            System.out.println("Cliente conectado!");

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter saida = new PrintWriter(
                    socket.getOutputStream(), true);

            String mensagem;

            while ((mensagem = entrada.readLine()) != null) {
                System.out.println("Cliente: " + mensagem);
                saida.println("Servidor recebeu: " + mensagem);

                if (mensagem.equalsIgnoreCase("sair")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Conexão encerrada.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
