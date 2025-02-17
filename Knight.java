import java.util.Arrays;
import java.util.Vector;

//Author: Didier A.L

public class Knight {
   
    public String pieceName;
    public String color;
    public String column;
    public String row;

    public Knight() {
        this.pieceName = "Knight";
        this.color = null;
        this.column = null;
        this.row = null;
    }

    public Knight(String color, String col, String row) {
        this.pieceName = "Knight";
        this.color = color.toLowerCase();
        this.column = col.toLowerCase();
        this.row = row;
    }

    public Knight(String color, String col, String row, String pieceName) {
        this.pieceName = pieceName;
        this.color = color.toLowerCase();
        this.column = col.toLowerCase();
        this.row = row;
    }

    public boolean inBounds(int[] coordinate) {
        int indexOne = coordinate[0];
        int indexTwo = coordinate[1];
        if (indexOne < 0 || indexOne > 7)
            return false;
        if (indexTwo < 0 || indexTwo > 7)
            return false;
        return true;
    }

    public int[][] vectorToArray(Vector<int[]> vector) {
        int[][] newArray = new int[vector.size()][2];
        for (int i = 0; i < vector.size(); i += 1) {
            int[] curr = { vector.get(i)[0], vector.get(i)[1] };
            newArray[i] = curr;
        }
        return newArray;
    }

    public int[][] generateCoordinates() {
        Vector<int[]> vector = new Vector<>();
        String letters = "abcdefgh";
        String numbers = "87654321";

        int rows = numbers.indexOf(this.row);
        int cols = letters.indexOf(this.column);

        int[] topLeft = { rows - 2, cols - 1 };
        int[] topRight = { rows - 2, cols + 1 };

        int[] leftTop = { rows - 1, cols - 2 };
        int[] leftBottom = { rows + 1, cols - 2 };

        int[] rightTop = { rows - 1, cols + 2 };
        int[] rightBottom = { rows + 1, cols + 2 };

        int[] bottomLeft = { rows + 2, cols - 1 };
        int[] bottomRight = { rows + 2, cols + 1 };

        if (inBounds(topLeft) == true)
            vector.add(topLeft);
        if (inBounds(topRight) == true)
            vector.add(topRight);
        if (inBounds(leftTop) == true)
            vector.add(leftTop);
        if (inBounds(leftBottom) == true)
            vector.add(leftBottom);
        if (inBounds(rightTop) == true)
            vector.add(rightTop);
        if (inBounds(rightBottom) == true)
            vector.add(rightBottom);
        if (inBounds(bottomLeft) == true)
            vector.add(bottomLeft);
        if (inBounds(bottomRight) == true)
            vector.add(bottomRight);

        return vectorToArray(vector);
    }

    public void setColumn(String col) {
        this.column = col.toLowerCase();
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getColumn() {
        return this.column;
    }

    public String getRow() {
        return this.row;
    }

    public String getColor() {
        return this.color;
    }

        public boolean verifyTarget(String column, String row) {
        String letters = "abcdefgh";
        String numbers = "87654321";
        int attackRows = numbers.indexOf(row);
        int attackCols = letters.indexOf(column.toLowerCase());

        int[] attackPosition = { attackRows, attackCols };
        int[][] availableCoordinates = this.generateCoordinates();

        for (int i = 0; i < availableCoordinates.length; i += 1) {
            int[] curr = availableCoordinates[i];
            if (Arrays.equals(curr, attackPosition)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Knight test = new Knight("red", "g", "1"); // change to class name
        System.out.println(test.verifyTarget("z", "2"));
    }
}