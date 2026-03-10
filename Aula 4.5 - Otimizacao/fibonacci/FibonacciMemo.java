import java.util.HashMap;

public class FibonacciMemo {

    static HashMap<Integer, Long> memo = new HashMap<>();

    public static long fib(int n) {

        if (n <= 1)
            return n;

        if (memo.containsKey(n))
            return memo.get(n);

        long resultado = fib(n - 1) + fib(n - 2);

        memo.put(n, resultado);

        return resultado;
    }

    public static void main(String[] args) {

        int n = 40;

        long inicio = System.currentTimeMillis();

        long resultado = fib(n);

        long fim = System.currentTimeMillis();

        System.out.println("Resultado: " + resultado);
        System.out.println("Tempo: " + (fim - inicio) + " ms");
    }
}