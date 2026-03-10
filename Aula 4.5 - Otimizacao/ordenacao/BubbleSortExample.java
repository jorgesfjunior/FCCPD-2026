import java.util.Random;

public class BubbleSortExample {

    public static void bubbleSort(int[] v) {
        int n = v.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {

                if (v[j] > v[j + 1]) {

                    int temp = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] vetor = new int[20000];
        Random rand = new Random();

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = rand.nextInt(100000);
        }

        long inicio = System.currentTimeMillis();

        bubbleSort(vetor);

        long fim = System.currentTimeMillis();

        System.out.println("Tempo BubbleSort: " + (fim - inicio) + " ms");
    }
}