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

        for (int i = 0; i < 8; i++) {
            if (i != cols) {
                int[] coordinate = { rows, i };
                if (inBounds(coordinate)) {
                    vector.add(coordinate);
                }
            }

            if (forceStop) {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (i != rows) {
                int[] coordinate = { i, cols };
                if (inBounds(coordinate)) {
                    vector.add(coordinate);
                }
            }

            if (forceStop) {
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

    public static void main(String[] args) {
        Queen test = new Queen("white", "g", "1");
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