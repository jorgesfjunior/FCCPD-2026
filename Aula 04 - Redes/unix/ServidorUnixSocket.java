import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.nio.file.*;

public class ServidorUnixSocket {
    public static void main(String[] args) throws Exception {

        Path path = Path.of("/tmp/socket_java");

        ServerSocketChannel server =
                ServerSocketChannel.open(StandardProtocolFamily.UNIX);

        server.bind(UnixDomainSocketAddress.of(path));

        SocketChannel cliente = server.accept();

        BufferedReader entrada = new BufferedReader(
                new InputStreamReader(cliente.socket().getInputStream()));

        System.out.println("Mensagem: " + entrada.readLine());

        cliente.close();
        server.close();
    }
}