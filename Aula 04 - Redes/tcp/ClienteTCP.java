import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 5000);

        PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        saida.println("Olá servidor!");

        String resposta = entrada.readLine();
        System.out.println("Servidor respondeu: " + resposta);

        socket.close();
    }
}