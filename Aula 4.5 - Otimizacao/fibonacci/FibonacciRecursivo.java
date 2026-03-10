public class FibonacciRecursivo {

    public static long fib(int n) {

        if (n <= 1)
            return n;

        return fib(n - 1) + fib(n - 2);
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