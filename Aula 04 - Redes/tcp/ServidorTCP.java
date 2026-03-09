import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Servidor aguardando conexão...");

        Socket socket = servidor.accept();

        BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);

        String mensagem = entrada.readLine();
        System.out.println("Cliente disse: " + mensagem);

        saida.println("Mensagem recebida!");

        socket.close();
        servidor.close();
    }
}