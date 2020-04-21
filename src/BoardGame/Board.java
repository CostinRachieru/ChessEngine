
package BoardGame;

import ChessPieces.*;
import Helper.Helper;

import java.util.ArrayList;

import static Helper.Constants.*;
import static Helper.Constants.LEFT_ROOK_CASTLING_COLUMN;

public class Board {
    // We do not use the line and column 0.
    private Piece[][] board = new Piece[10][10];
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> moveHistory;

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

    public boolean canCastle(final String team) {
        return getKing(team).canCastleKingSide() || getKing(team).canCastleQueenSide();
    }

    public final void init() {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        moveHistory = new ArrayList<>();
        PieceFactory pieceFactory = PieceFactory.getInstance();
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

    /* Return 0 if it is not a castling move, a positive number if it is Kingside castling or a
    negative number if it is a Queenside castling */
    private int isCastling(Piece piece, Position newPos) {
        // It is a king.
        if (!piece.getType().equals("King")) {
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

    public void doCastling(int line, int side) {
        if (side > 0) {
            Piece rook = getPiece(line, RIGHT_COLUMN);
            Position newRookPos = new Position(line, RIGHT_ROOK_CASTLING_COLUMN);
            movePiece(rook, newRookPos);
        } else {
            Piece rook = getPiece(line, LEFT_COLUMN);
            Position newRookPos = new Position(line, LEFT_ROOK_CASTLING_COLUMN);
            movePiece(rook, newRookPos);
        }
    }

    public final void movePiece(Piece piece, Position newPos) {
        PieceHistory pieceHistory = piece.createHistoy();
        int newLine = newPos.getLine();
        int newCol = newPos.getColumn();

        // check if piece captures other piece
        if (!isEmpty(newLine, newCol)) {
            Piece capturedPiece = getPiece(newLine, newCol);
            pieceHistory.setHasCaptured(true);
            pieceHistory.setCapturedPiece(capturedPiece);
            capturedPiece.setAlive(false);
        }

        // check if piece dose castling
        int castlingSide = isCastling(piece, newPos);
        if (castlingSide != 0) {
            doCastling(piece.getLine(), castlingSide);
            pieceHistory.setWasCastling(true);
        }

        board[piece.getLine()][piece.getColumn()] = null;
        board[newLine][newCol] = piece;
        piece.move(newPos);
        piece.addHistory(pieceHistory);

        moveHistory.add(piece);
    }

    public final void undoMove() {
        Piece piece = moveHistory.get(moveHistory.size() - 1);
        moveHistory.remove(moveHistory.size() - 1);
        PieceHistory pieceHistory = piece.getLastHistory();
        int currentLine = piece.getLine();
        int currentCol = piece.getColumn();
        int prevLine = pieceHistory.getPrevLine();
        int prevCol = pieceHistory.getPrevCol();

        board[currentLine][currentCol] = null;
        piece.restoreToLastState(pieceHistory);
        board[prevLine][prevCol] = piece;

        // if a piece was captured restore it
        if (pieceHistory.hasCaptured()) {
            Piece capturedPiece = pieceHistory.getCapturedPiece();
            capturedPiece.setAlive(true);
            board[currentLine][currentCol] = capturedPiece;
        }

        // if castling was done move the rook back
        if (pieceHistory.wasCastling()) {
            undoMove();
        }
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

    // DEBUG METHODS
    // print board for debugging
    public final void printBoard() {
        String res = "   A B C D E F G H\n";
        for (int i = 1; i <= 8; ++i) {
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
}
