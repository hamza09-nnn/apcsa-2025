package gol;

public class App {

    public static void main(String[] args) {
        // Example usage: create a 7x7 board
        GameOfLife g = new GameOfLife(7, 7);

        // Example pattern (glider)
        int[][] data = {
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1}
        };

        // Place pattern at position (1,1)
        g.set(1, 1, data);

        // Run 5 steps
        g.run(5);

        // Run the test suite
        TestSuite.run();
    }
}

