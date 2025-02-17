import java.util.Arrays;
import java.util.Scanner;

public class Lab2 {

    public static void main(String[] args) throws Exception {

        Scanner userInput = new Scanner(System.in);
        char startLetter = '\0';
        int startNumber = -1;
        char endLetter = '\0';
        int endNumber = -1;
        String userPiece = "\0";
        String userColor = "\0";
        String columns = "abcdefgh";
        String rows = "87654321";

        while (true) {

            // Loop used to get users starting piece
            while (true) {
                // Loop to get users piece
                while (true) {
                    System.out.println(
                            "Please enter the piece you would like to move: (PAWN, ROOK, KNIGHT, BISHOP, QUEEN, and KING)");
                    userPiece = userInput.nextLine();

                    userPiece = userPiece.toLowerCase();

                    if (userPiece.equals("pawn") || userPiece.equals("rook") || userPiece.equals("knight")
                            || userPiece.equals("bishop") || userPiece.equals("queen") || userPiece.equals("king")) {
                        break;

                    }

                    else {
                        System.out.println(
                                "Error! Please enter a valid piece you would like to move: (PAWN, ROOK, KNIGHT, BISHOP, QUEEN, and KING)");

                    }

                }
                // Loop to get users piece color
                while (true) {
                    System.out.println("Please enter the color of your piece: (WHITE or BLACK)");
                    userColor = userInput.nextLine();

                    userColor = userColor.toLowerCase();

                    if (userColor.equals("white") || userColor.equals("black")) {
                        break;

                    }

                    else {
                        System.out.println("Error! Please enter a valid color for your piece: (WHITE or BLACK)");

                    }

                }
                // Loop to get users piece column
                while (true) {
                    System.out.println("Please enter the column of your piece: (a - h)");
                    String userStartCol = userInput.nextLine();

                    userStartCol = userStartCol.toLowerCase();

                    if (userStartCol.length() == 1) {
                        char startCol = userStartCol.charAt(0);

                        if ((startCol >= 'a' && startCol <= 'h')) {
                            startLetter = startCol;
                            break;

                        }

                        else {
                            System.out.println("Error! Please enter a valid column: (a - h)");

                        }

                    }

                    else {
                        System.out.println("Error! Please enter only one digit: (a - h)");

                    }

                }
                // Loop to get users piece row
                while (true) {
                    System.out.println("Please enter the row of your piece: (1 - 8)");
                    String userStartRow = userInput.nextLine();

                    userStartRow = userStartRow.toLowerCase();

                    if (userStartRow.length() == 1) {
                        char startRow = userStartRow.charAt(0);

                        if (Character.isDigit(startRow)) {
                            startNumber = Character.getNumericValue(startRow);

                            if (startNumber >= 1 && startNumber <= 8)
                                break;

                            else
                                System.out.println("Error! Please enter a valid row: (1 - 8)");

                        }

                        else {
                            System.out.println("Error! Please enter a valid number: (1 - 8)");

                        }

                    }

                    else {
                        System.out.println("Error! Please enter only one digit: (1 - 8)");

                    }

                }

                break;

            }
            // Loop used to get users target position
            while (true) {
                boolean possible = false;
                // Loop used to get users target position column
                while (true) {
                    System.out.println("Please enter the column of your target position: (a - h)");
                    String userEndCol = userInput.nextLine();

                    userEndCol = userEndCol.toLowerCase();

                    if (userEndCol.length() == 1) {
                        char endCol = userEndCol.charAt(0);

                        if ((endCol >= 'a' && endCol <= 'h')) {
                            endLetter = endCol;
                            break;

                        }

                        else {
                            System.out.println("Error! Please enter a valid column: (a - h)");

                        }

                    }

                    else {
                        System.out.println("Error! Please enter only one character: (a - h)");

                    }

                }
                // Loop used to get users target position row
                while (true) {
                    System.out.println("Please enter the row of your target position: (1 - 8)");
                    String userEndRow = userInput.nextLine();

                    userEndRow = userEndRow.toLowerCase();

                    if (userEndRow.length() == 1) {
                        char endRow = userEndRow.charAt(0);

                        if (Character.isDigit(endRow)) {
                            endNumber = Character.getNumericValue(endRow);

                            if (endNumber >= 1 && endNumber <= 8)
                                break;

                            else
                                System.out.println("Error! Please enter a valid row: (1 - 8)");

                        }

                        else {
                            System.out.println("Error! Please enter a number: (1 - 8)");

                        }

                    }

                    else {
                        System.out.println("Error! Please enter only one digit: (1 - 8)");

                    }

                }

                if (startNumber == endNumber && startLetter == endLetter) {
                    System.out.println("Error! Your starting position and target position are the same");

                }

                else {
                    switch (userPiece) {

                        case "pawn":
                            break;

                        case "rook":
                            break;

                        case "knight":
                            break;

                        case "bishop":
                            Bishop temp = new Bishop(userPiece, userColor, startLetter, startNumber);

                            int[][] availableCoordinates = temp.verifyTarget(temp.getColumn(), temp.getRow());

                            int indexOne = rows.indexOf(String.valueOf(endNumber)); // We get the index coordinate of
                                                                                    // the move the user inputted
                            int indexTwo = columns.indexOf(endLetter);

                            int[] move = { indexOne, indexTwo };

                            for (int j = 0; j < availableCoordinates.length; j++) { // We go through the available
                                                                                    // coordinates and check if the user
                                                                                    // move is in there, boolean
                                                                                    // (possible) will be changed to
                                                                                    // true if found
                                if (Arrays.equals(move, availableCoordinates[j])) {
                                    possible = true;
                                    break;

                                }

                            }

                            if (possible)
                                System.out.println(userPiece + " at " + temp.getColumn() + ", " + temp.getRow()
                                        + " can move to " + endLetter + ", " + endNumber);

                            else
                                System.out.println(userPiece + " at " + temp.getColumn() + ", " + temp.getRow()
                                        + " can't move to " + endLetter + ", " + endNumber);

                            break;

                        case "queen":
                            break;

                        case "king":
                            break;

                    }

                    System.out.println(
                            "Would you like to verify another position for your piece using your original position: (YES or NO)");
                    String switchPos = userInput.nextLine();

                    switchPos = switchPos.toLowerCase();

                    if (switchPos.equals("no"))
                        break;

                }

            }

            System.out.println("Would you like to select another chess piece: (YES or NO)");
            String switchPiece = userInput.nextLine();

            switchPiece = switchPiece.toLowerCase();

            if (switchPiece.equals("no"))
                break;

        }

    }

}