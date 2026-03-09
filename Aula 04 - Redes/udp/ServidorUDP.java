import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket(6000);

        byte[] buffer = new byte[1024];

        DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);

        socket.receive(pacote);

        String mensagem = new String(pacote.getData(), 0, pacote.getLength());
        System.out.println("Cliente disse: " + mensagem);

        String resposta = "Mensagem recebida";
        byte[] respostaBytes = resposta.getBytes();

        DatagramPacket respostaPacote = new DatagramPacket(
                respostaBytes,
                respostaBytes.length,
                pacote.getAddress(),
                pacote.getPort()
        );

        socket.send(respostaPacote);

        socket.close();
    }
}