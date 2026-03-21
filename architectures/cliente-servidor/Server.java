import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {

    private static final int PORT = 5005;
    private static final int MAX_THREADS = 5;

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("Servidor rodando na porta " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            pool.execute(new ClientHandler(clientSocket));
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true)
            ) {
                String request = in.readLine();
                System.out.println("Recebido: " + request);

                // Simular processamento lento
                Thread.sleep((long) (Math.random() * 3000));

                // Simular erro aleatório
                if (Math.random() < 0.2) {
                    throw new RuntimeException("Erro interno!");
                }

                out.println("Pedido processado: " + request);

            } catch (Exception e) {
                System.out.println("Erro no servidor: " + e.getMessage());
            } finally {
                try { socket.close(); } catch (IOException ignored) {}
            }
        }
    }
}