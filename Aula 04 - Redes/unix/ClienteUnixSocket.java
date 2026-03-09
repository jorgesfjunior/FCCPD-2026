import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.nio.file.*;

public class ClienteUnixSocket {
    public static void main(String[] args) throws Exception {

        Path path = Path.of("/tmp/socket_java");

        SocketChannel socket =
                SocketChannel.open(UnixDomainSocketAddress.of(path));

        PrintWriter saida =
                new PrintWriter(socket.socket().getOutputStream(), true);

        saida.println("Olá via Unix Socket");

        socket.close();
    }
}