package gol;

public class TestSuite {
    public static void run() {
        System.out.println("Starting TestSuite");
        boolean pass = true;

        GameOfLife board = new GameOfLife(5, 5);

        int[][] data = {{1}};

        // Test 1: Single cell, zero neighbors
        board.set(2, 2, data);
        pass &= expect(board.countNeighbors(2, 2), 0, "Single live cell with zero neighbors");

        // Test 2: Add one neighbor
        board.set(1, 2, data);
        pass &= expect(board.countNeighbors(2, 2), 1, "Single live cell with one neighbor");

        // Test 3: Add second neighbor
        board.set(3, 2, data);
        pass &= expect(board.countNeighbors(2, 2), 2, "Single live cell with two neighbors");

        // Test 4: Step simulation and check cell updates
        board.step();
        board.print();
        pass &= expect(board.get(2, 3), 1, "Expect line to rotate");

        board.step();
        board.print();
        pass &= expect(board.get(1, 2), 1, "Expect line to rotate");

        if (pass) {
            System.out.println("--- TEST PASSED! Congrats! ---");
        } else {
            System.out.println("--- TEST FAILED! :( ---");
        }
    }

    private static boolean expect(int input, int expected, String comment) {
        if (input == expected) {
            return true;
        }
        System.out.println(comment + ", value: " + input + " not equal to expected: " + expected);
        return false;
    }
}
