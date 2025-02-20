import java.util.Vector;

public class Bishop extends Piece {

    public Bishop() {
        this.pieceName = "bishop";
    }

    public Bishop(String color, String col, String row) {
        super(color, col, row, "bishop");
    }

    public Bishop(String color, String col, String row, String pieceName) {
        super(color, col, row, pieceName);
    }

    public int[][] makeBishopCoordinates(boolean force1) {
        Vector<int[]> vector = new Vector<>();

        String letters = "abcdefgh";
        String numbers = "87654321";

        int row = numbers.indexOf(this.row);
        int col = letters.indexOf(this.column);

        int[][] directions = {
                { 1, -1 }, // Top Left
                { 1, 1 }, // Top Right
                { -1, -1 }, // Bottom Left
                { -1, 1 } // Bottom Right
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
        return makeBishopCoordinates(false);
    }

    public static void main(String[] args) {
        Bishop test = new Bishop("white", "g", "1");
        System.out.println(test.getColor());
        System.out.println(test.getColumn());
        System.out.println(test.getRow());

        int[][] arr = test.generateCoordinates();

        for (int i = 0; i < arr.length; i += 1) {
            int[] curArr = arr[i];
            System.out.println("(" + curArr[0] + "," + curArr[1] + ")");
        }
    }

}