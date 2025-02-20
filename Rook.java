import java.util.Vector;

public class Rook extends Piece {

    public Rook() {
        this.pieceName = "rook";
    }

    public Rook(String color, String col, String row) {
        super(color, col, row, "rook");
    }

    public Rook(String color, String col, String row, String pieceName) {
        super(color, col, row, pieceName);
    }

    public int[][] makeRookCoordinates(boolean force1) {
        Vector<int[]> vector = new Vector<>();

        String letters = "abcdefgh";
        String numbers = "87654321";

        int row = numbers.indexOf(this.row);
        int col = letters.indexOf(this.column);

        int[][] directions = {
                { 0, -1 },  // Left
                { 0, 1 },   // Right
                { -1, 0 },  // Down
                { 1, 0 }    // Up
        };

        for (int[] direction : directions) {
            int x = row, y = col;
            while (true) {
                x += direction[0];
                y += direction[1];

                if (!inBounds(new int[] { x, y }))
                    break;

                vector.add(new int[] { x, y });

                if (force1)
                    break;
            }
        }

        return vectorToArray(vector);
    }

    @Override
    public int[][] generateCoordinates() {
        return makeRookCoordinates(false);
    }
}