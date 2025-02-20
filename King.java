public class King extends Queen{

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
        int[][] arr = makeBishopCoordinates(true);
        int[][] arrTwo = makeRookCoordinates(true);

        int[][] newArray = new int[arr.length+ arrTwo.length][2];
        int newArrayIndex = 0;

        for (int i = 0; i < arr.length; i += 1) {
            int[] curr = { arr[i][0], arr[i][1] };
            newArray[newArrayIndex] = curr;
            newArrayIndex +=1;
        }

        for (int i = 0; i < arrTwo.length; i += 1) {
            int[] curr = { arrTwo[i][0], arrTwo[i][1] };
            newArray[newArrayIndex] = curr;
            newArrayIndex +=1;
        }
        return newArray;
    }
}