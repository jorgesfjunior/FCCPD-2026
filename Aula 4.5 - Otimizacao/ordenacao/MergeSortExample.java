import java.util.Random;

public class MergeSortExample {

    public static void merge(int[] v, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = v[l + i];

        for (int j = 0; j < n2; j++)
            R[j] = v[m + 1 + j];

        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2) {

            if (L[i] <= R[j]) {
                v[k] = L[i];
                i++;
            } else {
                v[k] = R[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            v[k++] = L[i++];
        }

        while (j < n2) {
            v[k++] = R[j++];
        }
    }

    public static void mergeSort(int[] v, int l, int r) {

        if (l < r) {

            int m = (l + r) / 2;

            mergeSort(v, l, m);
            mergeSort(v, m + 1, r);

            merge(v, l, m, r);
        }
    }

    public static void main(String[] args) {

        int[] vetor = new int[20000];
        Random rand = new Random();

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = rand.nextInt(100000);
        }

        long inicio = System.currentTimeMillis();

        mergeSort(vetor, 0, vetor.length - 1);

        long fim = System.currentTimeMillis();

        System.out.println("Tempo MergeSort: " + (fim - inicio) + " ms");
    }
}