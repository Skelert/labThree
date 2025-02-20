import java.util.Vector;

public class Queen extends Bishop {

    public Queen() {
        this.pieceName = "queen";
    }

    public Queen(String color, String col, String row) {
        super(color, col, row, "queen");
    }

    public Queen(String color, String col, String row, String pieceName) {
        super(color, col, row, pieceName);
    }

    public int[][] makeRookCoordinates(boolean forceStop) {
        Vector<int[]> vector = new Vector<>();

        String letters = "abcdefgh";
        String numbers = "87654321";

        int rows = numbers.indexOf(this.row);
        int cols = letters.indexOf(this.column);

        // up down left right
        for (int i = cols - 1; i >= 0; i -= 1) {
            int[] left = { rows, i };
            vector.add(left);
            if(forceStop){
                break;
            }
        }
        for (int i = cols + 1; i <= 7; i += 1) {
            int[] right = { rows, i };
            vector.add(right);
            if(forceStop){
                break;
            }
        }
        for (int i = rows - 1; i >= 0; i -= 1) {
            int[] up = { i, cols };
            vector.add(up);
            if(forceStop){
                break;
            }
        }
        for (int i = rows + 1; i <= 7; i += 1) {
            int[] down = { i, cols };
            vector.add(down);
            if(forceStop){
                break;
            }
        }
        return vectorToArray(vector);

    }

    @Override
    public int[][] generateCoordinates() {
        int[][] arr = makeBishopCoordinates(false);
        int[][] arrTwo = makeRookCoordinates(false);

        int length1 = arr.length;
        int length2 = arrTwo.length;

        int[][] result = new int[length1 + length2][2];

        System.arraycopy(arr, 0, result, 0, length1);
        System.arraycopy(arrTwo, 0, result, length1, length2);

        return result;
    }
}