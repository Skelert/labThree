import java.util.Scanner;

public class labThree {
    enum PieceType {
        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }

    enum PieceColor {
        WHITE,
        BLACK
    }

    enum Continue {
        YES,
        NO
    }

    enum Rows {
        R1,
        R2,
        R3,
        R4,
        R5,
        R6,
        R7,
        R8
    }

    enum Columns {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H
    }

    public static void clearTerminal() {
        System.out.print("\33[H\033[2J");
        System.out.flush();
    }

    public static String getPiece(String message, String tryAgain) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(message);
        String piece = userInput.nextLine().toUpperCase();
        for (PieceType myVar : PieceType.values()) {
            String curr = myVar.toString();
            if (curr.equals(piece)) {
                clearTerminal();
                return piece;
            }
        }
        return getPiece(tryAgain, tryAgain);
    }

    public static String getColor(String message, String tryAgain) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(message);
        String color = userInput.nextLine().toUpperCase();
        for (PieceColor myVar : PieceColor.values()) {
            String curr = myVar.toString();
            if (curr.equals(color)) {
                clearTerminal();
                return color;
            }
        }
        return getColor(tryAgain, tryAgain);
    }

    public static String getCoordinates(String message1, String message2, String tryAgain) {

        checkCoordinates coordinateCheck = new checkCoordinates();

        String col = getCol(message1, tryAgain);
        String row = getRow(message2, tryAgain);
        String chessCoordinates = "" + col + row;

        if (coordinateCheck.withinChessBoard(col, row)) {
            clearTerminal();
            return chessCoordinates;
        }

        return getCoordinates("position out of bounds, input your column again", message2, tryAgain);

    }

    public static String getCol(String message, String tryAgain) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(message);
        String col = userInput.nextLine().toUpperCase();
        for (Columns myVar : Columns.values()) {
            String curr = myVar.toString();
            if (curr.equals(col)) {
                clearTerminal();
                return col;
            }
        }

        return getCol(tryAgain, tryAgain);

    }

    public static String getRow(String message, String tryAgain) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(message);
        String row = userInput.nextLine().toUpperCase();
        for (Rows myVar : Rows.values()) {
            String curr = myVar.toString().substring(1, 2);
            if (curr.equals(row)) {
                clearTerminal();
                return row;
            }
        }

        return getRow(tryAgain, tryAgain);

    }

    public static String getContinue(String message, String tryAgain) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(message);
        String color = userInput.nextLine().toUpperCase();
        for (Continue myVar : Continue.values()) {
            String curr = myVar.toString();
            if (curr.equals(color)) {
                clearTerminal();
                return color;
            }
        }
        return getContinue(tryAgain, tryAgain);
    }

    public static Piece[] inUseArray = new Piece[6];

    public static String notInUse() {
        String notIn = "KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN";

        for (PieceType myVar : PieceType.values()) {
            String curr = myVar.toString();
            for (int i = 0; i < inUseArray.length; i++) {
                Piece curInUse = inUseArray[i];
                if (curInUse == null) {
                    continue;
                }
                if (curr.equals(curInUse.pieceName.toUpperCase())) {
                    if (curr.equals("PAWN")) {
                        notIn = notIn.replace(", " + curr, "");
                    }
                    notIn = notIn.replace(curr + ", ", "");
                }
            }
        }
        return notIn;
    }

    public static boolean isInUseArray(String piece) {
        for (int i = 0; i < inUseArray.length; i += 1) {
            Piece cur = inUseArray[i];
            if (cur == null)
                continue;

            if (cur.getPieceName().toLowerCase().equals(piece.toLowerCase()))
                return true;
        }
        return false;
    }

    public static Piece createChessPiece() {
        String piece = getPiece("what piece? options: (" + notInUse() + ")",
                "try again  options: (" + notInUse() + ")");
        while (isInUseArray(piece)) {
            piece = getPiece("sorry thats already been used try again", "try again");
        }
        String color = getColor("what color", "try again");
        String coordinates = getCoordinates("what is the starting column of your piece",
                "what is the starting row of your piece", "try again");

        String coordinateCol = coordinates.split("")[0];
        String coordinateRow = coordinates.split("")[1];

        Piece newCreatedPiece = new Piece();

        switch (piece) {
            case "KING":
                newCreatedPiece = new King(color, coordinateCol, coordinateRow);
                break;
            case "QUEEN":
                newCreatedPiece = new Queen(color, coordinateCol, coordinateRow);
                break;
            case "ROOK":
                newCreatedPiece = new Rook(color, coordinateCol, coordinateRow);
                break;
            case "KNIGHT":
                newCreatedPiece = new Knight(color, coordinateCol, coordinateRow);
                break;
            case "PAWN":
                newCreatedPiece = new Pawn(color, coordinateCol, coordinateRow);
                break;
            case "BISHOP":
                newCreatedPiece = new Bishop(color, coordinateCol, coordinateRow);
                break;
        }
        return newCreatedPiece;
    }

    public static void verifyEachPiece(String attackCoordinates) {
        String col = attackCoordinates.split("")[0];
        String row = attackCoordinates.split("")[1];
        for (int i = 0; i < inUseArray.length; i += 1) {
            Piece currPiece = inUseArray[i];
            boolean valid = currPiece.verifyTarget(col, row);
            if (attackCoordinates.equals(currPiece.getColumn() + "" + currPiece.getRow())) {
                System.out.println(currPiece.pieceName + " is already at " + attackCoordinates);
            } else if (valid) {
                System.out.println(currPiece.pieceName + " at " + currPiece.getColumn() + "" + currPiece.getRow()
                        + " can attack " + attackCoordinates);
            } else {
                System.out.println(currPiece.pieceName + " at " + currPiece.getColumn() + "" + currPiece.getRow()
                        + " can not attack " + attackCoordinates);
            }

        }
    }

    public static void setAllSixPieces() {
        for (int i = 0; i < inUseArray.length; i += 1) {
            inUseArray[i] = createChessPiece();
        }
    }

    public static void game() {
        setAllSixPieces();
        String target = getCoordinates("What is the column of your attack position",
                "What is the row of your attack position", "try again");
        verifyEachPiece(target);
    }

    public static void main(String[] args) {
        game();
    }
}