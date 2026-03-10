import java.util.HashMap;
import java.util.Map;

class User {
    int id;
    String nome;

    User(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}

public class BuscaHashMap {

    public static void main(String[] args) {

        Map<Integer, User> usuarios = new HashMap<>();

        for (int i = 0; i < 1000000; i++) {
            usuarios.put(i, new User(i, "User" + i));
        }

        long inicio = System.currentTimeMillis();

        User u = usuarios.get(999999);

        long fim = System.currentTimeMillis();

        System.out.println("Encontrado: " + u.nome);
        System.out.println("Tempo: " + (fim - inicio) + " ms");
    }
}