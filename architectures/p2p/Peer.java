import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Peer {

    private int port;
    private Set<Socket> connections = ConcurrentHashMap.newKeySet();
    private ExecutorService pool = Executors.newCachedThreadPool();

    public Peer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Peer rodando na porta " + port);

        // Thread para aceitar conexões
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    connections.add(socket);
                    handleConnection(socket);
                } catch (IOException e) {
                    System.out.println("Erro ao aceitar conexão");
                }
            }
        }).start();

        // Thread para enviar mensagens do terminal
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String msg = scanner.nextLine();
                broadcast("[" + port + "] " + msg);
            }
        }).start();
    }

    public void connectToPeer(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            connections.add(socket);
            handleConnection(socket);
            System.out.println("Conectado ao peer " + port);
        } catch (IOException e) {
            System.out.println("Falha ao conectar ao peer " + port);
        }
    }

    private void handleConnection(Socket socket) {
        pool.execute(() -> {
            try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))
            ) {
                String msg;
                while ((msg = in.readLine()) != null) {

                    // Simular falha aleatória
                    if (Math.random() < 0.1) {
                        throw new RuntimeException("Falha no peer!");
                    }

                    System.out.println("Recebido: " + msg);

                    // Repassar para outros peers
                    broadcast(msg, socket);
                }
            } catch (Exception e) {
                System.out.println("Conexão perdida com peer");
            } finally {
                connections.remove(socket);
                try { socket.close(); } catch (IOException ignored) {}
            }
        });
    }

    private void broadcast(String msg) {
        broadcast(msg, null);
    }

    private void broadcast(String msg, Socket origin) {
        for (Socket conn : connections) {
            if (conn != origin) {
                try {
                    PrintWriter out = new PrintWriter(
                        conn.getOutputStream(), true);
                    out.println(msg);
                } catch (IOException e) {
                    System.out.println("Erro ao enviar mensagem");
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(args[0]);
        Peer peer = new Peer(port);
        peer.start();

        // Conectar a peers iniciais (opcional)
        for (int i = 1; i < args.length; i++) {
            String[] parts = args[i].split(":");
            peer.connectToPeer(parts[0], Integer.parseInt(parts[1]));
        }
    }
}