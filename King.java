import java.util.Vector;
//Author: Didier A.L

public class King extends Piece { // change class name

    public King() {
        this.pieceName = "king";
    }

    public King(String color, String col, String row) {
        super(color, col, row, "king");
    }

    public King(String color, String col, String row, String pieceName) {
        super(color, col, row, pieceName);
    }

    @Override
    public int[][] generateCoordinates() {
        Vector<int[]> vector = new Vector<>();
        String letters = "abcdefgh";
        String numbers = "87654321";

        int rows = numbers.indexOf(this.row);
        int cols = letters.indexOf(this.column);

        int[] top = { rows - 1, cols };
        int[] topLeft = { rows - 1, cols - 1 };
        int[] topRight = { rows - 1, cols + 1 };

        int[] left = { rows, cols - 1 };
        int[] right = { rows, cols + 1 };

        int[] bottom = { rows + 1, cols };
        int[] bottomLeft = { rows + 1, cols - 1 };
        int[] bottomRight = { rows + 1, cols + 1 };

        if (inBounds(top))
            vector.add(top);
        if (inBounds(topLeft))
            vector.add(topLeft);
        if (inBounds(topRight))
            vector.add(topRight);
        if (inBounds(left))
            vector.add(left);
        if (inBounds(right))
            vector.add(right);
        if (inBounds(bottom))
            vector.add(bottom);
        if (inBounds(bottomLeft))
            vector.add(bottomLeft);
        if (inBounds(bottomRight))
            vector.add(bottomRight);

        return vectorToArray(vector);
    }
}