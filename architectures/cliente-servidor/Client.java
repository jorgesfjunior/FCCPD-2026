import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 5005;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            int id = i;
            pool.execute(() -> sendRequest("Pedido-" + id));
        }

        pool.shutdown();
    }

    private static void sendRequest(String message) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(HOST, PORT), 2000);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            out.println(message);

            socket.setSoTimeout(2000); // timeout leitura

            String response = in.readLine();
            System.out.println("Resposta: " + response);

        } catch (SocketTimeoutException e) {
            System.out.println("Timeout no cliente para: " + message);
        } catch (IOException e) {
            System.out.println("Erro conexão: " + message);
        }
    }
}