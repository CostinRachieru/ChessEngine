package BoardGame;

import ChessPieces.Piece;
import ChessPieces.PieceFactory;
import ChessPieces.Position;
import Helper.Helper;

import java.util.ArrayList;

public class Board {
    // We do not use the line and column 0.
    private Piece[][] board = new Piece[10][10];
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private ArrayList<Boolean> wasCaptured = new ArrayList<Boolean>();
    private ArrayList<Piece> whiteCapturedPieces = new ArrayList<Piece>();
    private ArrayList<Piece> blackCapturedPieces = new ArrayList<Piece>();

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
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        PieceFactory pieceFactory = PieceFactory.getInstance();
        board[1][1] = pieceFactory.createPiece("Rook", 1, 1, "White");
        whitePieces.add(board[1][1]);
        board[1][2] = pieceFactory.createPiece("Knight", 1, 2, "White");
        whitePieces.add(board[1][2]);
        board[1][3] = pieceFactory.createPiece("Bishop", 1, 3, "White");
        whitePieces.add(board[1][3]);
        board[1][4] = pieceFactory.createPiece("King", 1, 4, "White");
        whitePieces.add(board[1][4]);
        board[1][5] = pieceFactory.createPiece("Queen", 1, 5, "White");
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
        board[8][4] = pieceFactory.createPiece("King", 8, 4, "Black");
        blackPieces.add(board[8][4]);
        board[8][5] = pieceFactory.createPiece("Queen", 8, 5, "Black");
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
        if (board[line][column] == null) {
            return true;
        }
        return false;
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

    public final Piece getKing(String team) {
        if (team.equals("Black")) {
            for (Piece piece : blackPieces) {
                if (piece.getType().equals("King")) {
                    return piece;
                }
            }
        } else {
            for (Piece piece : whitePieces) {
                if (piece.getType().equals("King")) {
                    return piece;
                }
            }
        }
        return null;
    }

    public final ArrayList<Piece> getPawns(final String color) {
        ArrayList<Piece> pawns = new ArrayList<>();
        if (color.equals("Black")) {
            for (int i = 0 ; i < blackPieces.size(); ++i) {
                if (blackPieces.get(i).getType().equals("Pawn")) {
                    pawns.add(blackPieces.get(i));
                }
            }
        } else {
            for (int i = 0; i < whitePieces.size(); ++i) {
                if (whitePieces.get(i).getType().equals("Pawn")) {
                    pawns.add(whitePieces.get(i));
                }
            }
        }
        return pawns;
    }

    // TODO: improve capture with getPiece function
    private final void capture(Piece lostPiece) {
        board[lostPiece.getLine()][lostPiece.getColumn()] = null;
        if (lostPiece.getTeam().equals("White")) {
            for (int i = 0; i < whitePieces.size(); ++i) {
                Piece piece = whitePieces.get(i);
                if (piece.getLine() == lostPiece.getLine() && piece.getColumn() == lostPiece.getColumn()) {
                    whitePieces.remove(i);
                    whiteCapturedPieces.add(piece);
                }
            }
        } else {
            for (int i = 0; i < blackPieces.size(); ++i) {
                Piece piece = blackPieces.get(i);
                if (piece.getLine() == lostPiece.getLine() && piece.getColumn() == lostPiece.getColumn()) {
                    blackPieces.remove(i);
                    blackCapturedPieces.add(piece);
                }
            }
        }
    }

    public final void movePiece(Piece piece, Position newPos) {
        if (!isEmpty(newPos.getLine(), newPos.getColumn())) {
            Piece lostPiece = getPiece(newPos.getLine(), newPos.getColumn());
            capture(lostPiece);
            wasCaptured.add(true);
        } else {
            wasCaptured.add(false);
        }
        board[piece.getLine()][piece.getColumn()] = null;
        board[newPos.getLine()][newPos.getColumn()] = piece;
        piece.move(newPos);
    }

    // Move piece back to the old position. Place back on the board any captured piece
    public final void undoMove(Piece piece) {
        Position oldPos = new Position(piece.getPrevLine(), piece.getPrevColumn());
        Piece enemyPiece;

        movePiece(piece, oldPos);
        boolean restoreCapturedPiece = wasCaptured.get(wasCaptured.size() - 1);
        wasCaptured.remove(wasCaptured.size() - 1);
        if (restoreCapturedPiece) {
            String enemyTeam = Helper.enemyTeam(piece.getTeam());
            if (enemyTeam.equals("White")) {
                enemyPiece = whiteCapturedPieces.get(whiteCapturedPieces.size() - 1);
                whiteCapturedPieces.remove(whiteCapturedPieces.size() - 1);
                whitePieces.add(enemyPiece);
            } else {
                enemyPiece = blackCapturedPieces.get(blackCapturedPieces.size() - 1);
                blackCapturedPieces.remove(blackCapturedPieces.size() - 1);
                blackPieces.add(enemyPiece);
            }
            board[enemyPiece.getLine()][enemyPiece.getColumn()] = enemyPiece;
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

}
