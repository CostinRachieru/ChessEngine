
package BoardGame;

import ChessPieces.King;
import ChessPieces.Piece;
import ChessPieces.PieceFactory;
import ChessPieces.Position;
import Helper.Helper;

import java.util.ArrayList;

import static Helper.Constants.LEFT_COLUMN;
import static Helper.Constants.RIGHT_COLUMN;

public class Board {
    // We do not use the line and column 0.
    private Piece[][] board = new Piece[10][10];
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private ArrayList<Boolean> wasCaptured;
    private ArrayList<Piece> whiteCapturedPieces;
    private ArrayList<Piece> blackCapturedPieces;
    private ArrayList<Piece> piecesCaptured;
    private ArrayList<Piece> movedPieces;

    private static Board instance = null;

    private Board() {
        init();
    }

    public final static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public final void init() {
        wasCaptured = new ArrayList<>();
        whiteCapturedPieces = new ArrayList<>();
        blackCapturedPieces = new ArrayList<>();
        piecesCaptured = new ArrayList<>();
        movedPieces = new ArrayList<>();
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        PieceFactory pieceFactory = PieceFactory.getInstance();
        board = new Piece[10][10];
        board[1][1] = pieceFactory.createPiece("Rook", 1, 1, "White");
        whitePieces.add(board[1][1]);
        board[1][2] = pieceFactory.createPiece("Knight", 1, 2, "White");
        whitePieces.add(board[1][2]);
        board[1][3] = pieceFactory.createPiece("Bishop", 1, 3, "White");
        whitePieces.add(board[1][3]);
        board[1][4] = pieceFactory.createPiece("Queen", 1, 4, "White");
        whitePieces.add(board[1][4]);
        board[1][5] = pieceFactory.createPiece("King", 1, 5, "White");
        whitePieces.add(board[1][5]);
        board[1][6] = pieceFactory.createPiece("Bishop", 1, 6, "White");
        whitePieces.add(board[1][6]);
        board[1][7] = pieceFactory.createPiece("Knight", 1, 7, "White");
        whitePieces.add(board[1][7]);
        board[1][8] = pieceFactory.createPiece("Rook", 1,8, "White");
        whitePieces.add(board[1][8]);

        board[8][1] = pieceFactory.createPiece("Rook", 8, 1, "Black");
        blackPieces.add(board[8][1]);
        board[8][2] = pieceFactory.createPiece("Knight", 8, 2, "Black");
        blackPieces.add(board[8][2]);
        board[8][3] = pieceFactory.createPiece("Bishop", 8, 3, "Black");
        blackPieces.add(board[8][3]);
        board[8][4] = pieceFactory.createPiece("Queen", 8, 4, "Black");
        blackPieces.add(board[8][4]);
        board[8][5] = pieceFactory.createPiece("King", 8, 5, "Black");
        blackPieces.add(board[8][5]);
        board[8][6] = pieceFactory.createPiece("Bishop", 8, 6, "Black");
        blackPieces.add(board[8][6]);
        board[8][7] = pieceFactory.createPiece("Knight", 8, 7, "Black");
        blackPieces.add(board[8][7]);
        board[8][8] = pieceFactory.createPiece("Rook", 8, 8, "Black");
        blackPieces.add(board[8][8]);

        for (int i = 1; i < 9; ++i) {
            board[2][i] = pieceFactory.createPiece("Pawn", 2, i, "White");
            whitePieces.add(board[2][i]);
            board[7][i] = pieceFactory.createPiece("Pawn", 7, i, "Black");
            blackPieces.add(board[7][i]);
        }
    }

    public final boolean isEmpty(final Integer line, final Integer column) {
        return board[line][column] == null;
    }

    public final Piece getPiece(Integer line, Integer column) {
        return board[line][column];
    }

    public final ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public final ArrayList<Piece> getBlackPieces() {
        return blackPieces;
    }

    public final King getKing(String team) {
        if (team.equals("Black")) {
            for (Piece piece : blackPieces) {
                if (piece.getType().equals("King")) {
                    return (King) piece;
                }
            }
        } else {
            for (Piece piece : whitePieces) {
                if (piece.getType().equals("King")) {
                    return (King) piece;
                }
            }
        }
        return null;
    }

    /**
     *  Method used to determine if a move leaves the king in check
     * @param piece piece to be moved
     * @param newPos position where piece will be moved
     * @return true if the move is valid, false if the move puts the king in check
     */
    public final boolean isMoveValid(final Piece piece, final Position newPos) {
        movePiece(piece, newPos);
        King king = getKing(piece.getTeam());
        if (king != null) {
            if (king.isCheck(king.getLine(), king.getColumn())) {
                return false;
            }
        }
        undoMove();
        return true;
    }

    public final ArrayList<Piece> getPawns(final String color) {
        ArrayList<Piece> pawns = new ArrayList<>();
        if (color.equals("Black")) {
            for (Piece blackPiece : blackPieces) {
                if (blackPiece.getType().equals("Pawn")) {
                    pawns.add(blackPiece);
                }
            }
        } else {
            for (Piece whitePiece : whitePieces) {
                if (whitePiece.getType().equals("Pawn")) {
                    pawns.add(whitePiece);
                }
            }
        }
        return pawns;
    }

    // TODO: improve capture with getPiece function
    private void capture(Piece lostPiece) {
        board[lostPiece.getLine()][lostPiece.getColumn()] = null;
        piecesCaptured.add(lostPiece);
        lostPiece.kill();
//        if (lostPiece.getTeam().equals("White")) {
////            whitePieces.remove(lostPiece);
//            whiteCapturedPieces.add(lostPiece);
//        } else {
//            blackPieces.remove(lostPiece);
//            blackCapturedPieces.add(lostPiece);
//        }
    }

    /* Return 0 if it is not a castling move, a positive number if it is Kingside castling or a
    negative number if it is a Queenside castling */
    private int isCastling(Piece piece, Position newPos) {
        // It is a king.
        if (!piece.getType().equals("King")) {
            return 0;
        }
        // Never moved.
        if (piece.getHadMoved() == true) {
            return 0;
        }
        // Moves on the same line
        if (piece.getLine() != newPos.getLine()) {
            return 0;
        }
        // Moves two squares on the same line
        int squaresMoved = newPos.getColumn() - piece.getColumn();
        if (Math.abs(squaresMoved) == 1) {
            return 0;
        }
        return squaresMoved;
    }

    public final void movePiece(Piece piece, Position newPos) {
        int castlingType = isCastling(piece, newPos);
        // Assumes that the castling move is valid.
        // TODO: undo move for castling.
        if (castlingType != 0) {
            // Move the king the board.
            board[piece.getLine()][piece.getColumn()] = null;
            board[newPos.getLine()][newPos.getColumn()] = piece;
            // Moves the rook on the board.
            if (castlingType < 0) {
                Piece rook = getPiece(piece.getLine(), LEFT_COLUMN);
                board[piece.getLine()][LEFT_COLUMN] = null;
                board[piece.getLine()][newPos.getColumn() + 1] = rook;
                rook.move(new Position(piece.getLine(), newPos.getColumn() + 1));
            } else {
                Piece rook = getPiece(piece.getLine(), RIGHT_COLUMN);
                board[piece.getLine()][RIGHT_COLUMN] = null;
                board[piece.getLine()][newPos.getColumn() - 1] = rook;
                rook.move(new Position(piece.getLine(), newPos.getColumn() - 1));
            }
            piece.move(newPos);
        } else {
            if (!isEmpty(newPos.getLine(), newPos.getColumn())) {
                Piece lostPiece = getPiece(newPos.getLine(), newPos.getColumn());
                capture(lostPiece);
                wasCaptured.add(true);
//                System.out.println("!@#");
            } else {
                wasCaptured.add(false);
            }
            movedPieces.add(piece);
            board[piece.getLine()][piece.getColumn()] = null;
            board[newPos.getLine()][newPos.getColumn()] = piece;
            piece.move(newPos);
        }
    }

    // Move piece back to the old position. Place back on the board any captured piece
//    public final void undoMove(Piece piece) {
//        Position oldPos = new Position(piece.getPrevLine(), piece.getPrevColumn());
//        Piece enemyPiece;
//
//        movePiece(piece, oldPos);
//        boolean restoreCapturedPiece = wasCaptured.get(wasCaptured.size() - 1);
//        wasCaptured.remove(wasCaptured.size() - 1);
//        if (restoreCapturedPiece) {
//            String enemyTeam = Helper.enemyTeam(piece.getTeam());
//            if (enemyTeam.equals("White")) {
//                enemyPiece = whiteCapturedPieces.get(whiteCapturedPieces.size() - 1);
//                whiteCapturedPieces.remove(whiteCapturedPieces.size() - 1);
//                whitePieces.add(enemyPiece);
//            } else {
//                enemyPiece = blackCapturedPieces.get(blackCapturedPieces.size() - 1);
//                blackCapturedPieces.remove(blackCapturedPieces.size() - 1);
//                blackPieces.add(enemyPiece);
//            }
//            board[enemyPiece.getLine()][enemyPiece.getColumn()] = enemyPiece;
//        }
//    }

    public final void undoMove() {
//        System.out.println("Before: ");
//        printBoard();
        Piece movedPiece = movedPieces.get(movedPieces.size() - 1);
        movedPieces.remove(movedPieces.size() - 1);
        ArrayList<Integer> prevLines = movedPiece.getPrevLine();
        ArrayList<Integer> prevColumns = movedPiece.getPrevColumn();

        int prevLine = prevLines.get(prevLines.size() - 1);
        int prevColumn = prevColumns.get(prevColumns.size() - 1);

        movedPiece.getPrevLine().remove(movedPiece.getPrevLine().size() - 1);
        movedPiece.getPrevColumn().remove(movedPiece.getPrevColumn().size() - 1);

        movePiece(movedPiece, new Position(prevLine, prevColumn));
        movedPiece.getPrevLine().remove(movedPiece.getPrevLine().size() - 1);
        movedPiece.getPrevColumn().remove(movedPiece.getPrevColumn().size() - 1);
        movedPieces.remove(movedPieces.size() - 1);
        wasCaptured.remove(wasCaptured.size() - 1);

        boolean restoreCapturedPiece = wasCaptured.get(wasCaptured.size() - 1);
        wasCaptured.remove(wasCaptured.size() - 1);
        if (restoreCapturedPiece && piecesCaptured.size() != 0) {
            Piece pieceCaptured = piecesCaptured.get(piecesCaptured.size() - 1);
            piecesCaptured.remove(piecesCaptured.size() - 1);
            board[pieceCaptured.getLine()][pieceCaptured.getColumn()] = pieceCaptured;
            pieceCaptured.revive();
//            if (pieceCaptured.getTeam().equals("Black")) {
//                blackPieces.add(pieceCaptured);
//            } else {
//                whitePieces.add(pieceCaptured);
//            }
        }
//        System.out.println("After:");
//        printBoard();
    }

    public final void moveEnemyPiece(String move) {
        Position prevPos = new Position(move.substring(0,2));
        Position newPos = new Position(move.substring(2,4));
        Piece piece = getPiece(prevPos.getLine(), prevPos.getColumn());
        if (piece != null) movePiece(piece, newPos);
    }

    public final ArrayList<Piece> getPieces(String team) {
        if (team.equals("White")) {
            return getWhitePieces();
        } else {
            return getBlackPieces();
        }
    }

    // print board for debugging
    public final void printBoard() {
        String res = "   A B C D E F G H\n";
        for (int i = 8; i >= 1; --i) {
            res = res + i + " |";
            for (int j = 1; j <= 8; ++j) {
                if (board[i][j] != null) {
                    res += pieceSymbol(board[i][j]) + "|";
                } else {
                    res += " |";
                }
            }
            res += "\n";
        }
        System.out.println(res);
        System.out.flush();
    }

    public boolean canCastle(final String team) {
        return getKing(team).canCastleKingSide() || getKing(team).canCastleQueenSide();
    }

    public final char pieceSymbol(Piece piece) {
        String pieceType = piece.getType();
        if (piece.getTeam().equals("White")) {
            switch (pieceType) {
                case "King": return 'K';
                case "Queen": return 'Q';
                case "Rook": return 'R';
                case "Bishop": return 'B';
                case "Knight": return 'N';
                case "Pawn": return 'P';
                default : return 'X';
            }
        } else {
            switch (pieceType) {
                case "King": return 'k';
                case "Queen": return 'q';
                case "Rook": return 'r';
                case "Bishop": return 'b';
                case "Knight": return 'n';
                case "Pawn": return 'p';
                default : return 'X';
            }
        }
    }

//    public void setWhitePieces(ArrayList<Piece> whitePieces) {
//        this.whitePieces = whitePieces;
//    }
//
//    public void setBlackPieces(ArrayList<Piece> blackPieces) {
//        this.blackPieces = blackPieces;
//    }
//
//    public void setPieces(ArrayList<Piece> pieces, String team) {
//        if (team.equals("White")) {
//            setWhitePieces(pieces);
//        } else {
//            setBlackPieces(pieces);
//        }
//    }
}
