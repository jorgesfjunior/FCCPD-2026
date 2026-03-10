import java.util.ArrayList;
import java.util.List;

class User {
    int id;
    String nome;

    User(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}

public class BuscaLinear {

    public static User buscarUsuario(List<User> usuarios, int id) {
        for (User u : usuarios) {
            if (u.id == id) {
                return u;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        List<User> usuarios = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            usuarios.add(new User(i, "User" + i));
        }

        long inicio = System.currentTimeMillis();

        User u = buscarUsuario(usuarios, 999999);

        long fim = System.currentTimeMillis();

        System.out.println("Encontrado: " + u.nome);
        System.out.println("Tempo: " + (fim - inicio) + " ms");
    }
}