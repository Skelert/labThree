import java.util.Vector;

public class Pawn extends Piece {

    public Pawn() {
        this.pieceName = "pawn";
    }

    public Pawn(String color, String col, String row) {
        super(color, col, row, "pawn");
    }

    public Pawn(String color, String col, String row, String pieceName) {
        super(color, col, row, pieceName);
    }

    public int[][] makePawnCoordinates(boolean force1) {
        Vector<int[]> vector = new Vector<>();

        String letters = "abcdefgh";
        String numbers = "87654321";

        int row = numbers.indexOf(this.row);
        int col = letters.indexOf(this.column);

        // Determine direction based on color
        int direction = this.color.equals("white") ? -1 : 1;

        // First move
        int newRow = row + direction;
        if (inBounds(new int[]{newRow, col})) {
            vector.add(new int[]{newRow, col});

           
        }

        return vectorToArray(vector);
    }

    @Override
    public int[][] generateCoordinates() {
        return makePawnCoordinates(false);
    }
}