package sorting;

public class SelectionSort implements Sorter {

    public static long steps = 0;

    public void sort(int[] input) {

        steps = 0;

        for (int i = 0; i < input.length - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < input.length; j++) {
                steps++; // comparison step
                if (input[j] < input[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = input[i];
            input[i] = input[minIndex];
            input[minIndex] = temp;
            steps++; // swap step
        }

        System.out.println("SelectionSort steps: " + steps);
    }
}