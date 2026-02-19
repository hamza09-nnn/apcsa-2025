package sorting;

public class InsertionSort implements Sorter {

    public static long steps = 0;

    public void sort(int[] input) {

        steps = 0;

        for (int i = 1; i < input.length; i++) {

            int key = input[i];
            int j = i - 1;

            while (j >= 0 && input[j] > key) {
                steps++; // comparison
                input[j + 1] = input[j];
                j--;
                steps++; // shift
            }

            input[j + 1] = key;
            steps++; // insertion
        }

        System.out.println("InsertionSort steps: " + steps);
    }
}
