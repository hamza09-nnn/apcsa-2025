package sorting;

import java.util.Random;

public class Main {

    public static void main(String[] args)
    {
        int[] testInput = randomArray(100);

        // Increase loop count for better measurement
        TestSuite.run(testInput, 1);
    }

    public static int[] randomArray(int length)
    {
        Random rand = new Random();
        int[] array = new int[length];

        for (int i = 0; i < length; i++)
        {
            array[i] = rand.nextInt(1000);
        }

        return array;
    }
}