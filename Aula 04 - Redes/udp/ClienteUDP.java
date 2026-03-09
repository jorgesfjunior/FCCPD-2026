import java.net.*;

public class ClienteUDP {
    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket();

        String mensagem = "Olá servidor UDP";
        byte[] dados = mensagem.getBytes();

        InetAddress endereco = InetAddress.getByName("localhost");

        DatagramPacket pacote = new DatagramPacket(
                dados,
                dados.length,
                endereco,
                6000
        );

        socket.send(pacote);

        byte[] buffer = new byte[1024];
        DatagramPacket resposta = new DatagramPacket(buffer, buffer.length);

        socket.receive(resposta);

        String msg = new String(resposta.getData(), 0, resposta.getLength());
        System.out.println("Servidor respondeu: " + msg);

        socket.close();
    }
}