import java.util.Arrays;
import java.util.Vector;

//Author: Didier A.L

public class Queen {
    public String pieceName;
    public String color;
    public String column;
    public String row;

    public Queen() {
        this.pieceName = "Queen";
        this.color = null;
        this.column = null;
        this.row = null;
    }

    public Queen(String color, String col, String row) {
        this.pieceName = "Queen";
        this.color = color.toLowerCase();
        this.column = col.toLowerCase();
        this.row = row;
    }

    public Queen(String color, String col, String row, String pieceName) {
        this.pieceName = pieceName;
        this.color = color.toLowerCase();
        this.column = col.toLowerCase();
        this.row = row;
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

        // up down left right
        for (int i = cols - 1; i >= 0; i -= 1) {
            int[] left = { rows, i };
            vector.add(left);
        }
        for (int i = cols + 1; i <= 7; i += 1) {
            int[] right = { rows, i };
            vector.add(right);
        }
        for (int i = rows - 1; i >= 0; i -= 1) {
            int[] up = { i, cols };
            vector.add(up);
        }
        for (int i = rows + 1; i <= 7; i += 1) {
            int[] down = { i, cols };
            vector.add(down);
        }

        // diagonal movement
        int copyRows = rows - 1;
        int copyCols = cols + 1;
        while (copyRows >= 0 && copyCols <= 7) {
            int[] topRight = { copyRows, copyCols };
            vector.add(topRight);
            copyRows -= 1;
            copyCols += 1;
        }

        copyRows = rows - 1;
        copyCols = cols - 1;
        while (copyRows >= 0 && copyCols >= 0) {
            int[] topLeft = { copyRows, copyCols };
            vector.add(topLeft);
            copyRows -= 1;
            copyCols -= 1;
        }

        copyRows = rows + 1;
        copyCols = cols - 1;
        while (copyRows <= 7 && copyCols >= 0) {
            int[] bottomLeft = { copyRows, copyCols };
            vector.add(bottomLeft);
            copyRows += 1;
            copyCols -= 1;
        }

        copyRows = rows + 1;
        copyCols = cols + 1;
        while (copyRows <= 7 && copyCols <= 7) {
            int[] bottomRight = { copyRows, copyCols };
            vector.add(bottomRight);
            copyRows += 1;
            copyCols += 1;
        }

        return vectorToArray(vector);
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
}