import java.util.Scanner;

public class labTwoDD {

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

    public static String getCoordinates(String message, String tryAgain) {
        checkCoordinates coordinateCheck = new checkCoordinates();
        Scanner userInput = new Scanner(System.in);
        System.out.println(message);
        String chessCoordinates = userInput.nextLine();
        String col = chessCoordinates.split("")[0];
        String row = chessCoordinates.split("")[1];

        if (chessCoordinates.length() != 2) {
            return getCoordinates("must be two characters", tryAgain);
        }

        if (coordinateCheck.withinChessBoard(col, row)) {
            clearTerminal();
            return chessCoordinates;
        }
        return getCoordinates(tryAgain, tryAgain);
    }

    public static void printVerify(String pieceName, String col, String row, String target, Boolean valid) {
        if (valid) {
            System.out.println(pieceName + " at " + col + ", " + row + " can move to " + target);
        } else {
            System.out.println(pieceName + " at " + col + ", " + row + " can not move to " + target);
        }
    }

    public static void verifyTarget(String piece, String target, String color, String coordinates) {
        String coordinateCol = coordinates.split("")[0];
        String coordinateRow = coordinates.split("")[1];
        String targetCol = target.split("")[0];
        String targetRow = target.split("")[1];
        boolean canAttack;
        switch (piece) {
            
            case "KING":
                King kingTemp = new King(color, coordinateCol, coordinateRow);
                canAttack = kingTemp.verifyTarget(targetCol, targetRow);
                printVerify(piece, kingTemp.getColumn(), kingTemp.getRow(), target, canAttack);
                break;
            case "QUEEN":
                Queen queenTemp = new Queen(color, coordinateCol, coordinateRow);
                canAttack = queenTemp.verifyTarget(targetCol, targetRow);
                printVerify(piece, queenTemp.getColumn(), queenTemp.getRow(), target, canAttack);
                break;
            case "ROOK":
                Rook rookTemp = new Rook(color, coordinateCol, coordinateRow);
                canAttack = rookTemp.verifyTarget(targetCol, targetRow);
                printVerify(piece, rookTemp.getColumn(), rookTemp.getRow(), target, canAttack);
                break;
            case "KNIGHT":
                Knight knightTemp = new Knight(color, coordinateCol, coordinateRow);
                canAttack = knightTemp.verifyTarget(targetCol, targetRow);
                printVerify(piece, knightTemp.getColumn(), knightTemp.getRow(), target, canAttack);
                break;
            case "PAWN":
                Pawn pawnTemp = new Pawn(color, coordinateCol, coordinateRow);
                canAttack = pawnTemp.verifyTarget(targetCol, targetRow);
                printVerify(piece, pawnTemp.getColumn(), pawnTemp.getRow(), target, canAttack);
                break;
            case "BISHOP":
                Bishop bishopTemp = new Bishop(color, coordinateCol, coordinateRow);
                canAttack = bishopTemp.verifyTarget(targetCol, targetRow);
                printVerify(piece, bishopTemp.getColumn(), bishopTemp.getRow(), target, canAttack);
                break;
        }

        String verifyAgain = getContinue(
                "interested in verifying another target position using the same original position", "yes or no only");
        if (verifyAgain.equals("YES")) {
            String newTarget = getCoordinates("target coordinates", "try again");
            verifyTarget(piece, newTarget, color, coordinates);
        }
    }

    public static void game() {
        String piece = getPiece("Which Piece", "try again");
        String color = getColor("which color", "try again");
        String coordinates = getCoordinates("chess coordinates", "try again plz");
        String target = getCoordinates("target coordinates", "try again plz");

        verifyTarget(piece, target, color, coordinates);

        String gameAgain = getContinue("interested in selecting another chess piece", "yes or no only");
        if (gameAgain.equals("YES")) {
            game();
        } else {
            System.out.println("game terminated thanks for playing  |_(-_-)_/");
        }
    }

    public static void main(String[] args) {
        game();
    }
}