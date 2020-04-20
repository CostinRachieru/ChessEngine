package GameMechanics;

import BoardGame.Board;
import ChessPieces.*;
import Helper.Helper;

import java.util.ArrayList;

public class GamePlayer {
    private static final int MAX_DEPTH = 3;
    private static final int INITIAL_DEPTH = 1;
    private static final boolean INITIAL_IS_MAXIMIZING = false;
    private static final int KING_POINTS = 900;
    private static final int QUEEN_POINTS = 90;
    private static final int ROOK_POINTS = 50;
    private static final int KNIGHT_POINTS = 30;
    private static final int BISHOP_POINTS = 30;
    private static final int PAWN_POINTS = 10;

    private Board board;
    private String team;

    public GamePlayer(Board board) {
        this.board = board;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    // function returns a string representing the next move as a XBoard command
    public String playTurn(String team) {
        int score, maxScore = Integer.MIN_VALUE;
        String nextMove;
        Piece bestPiece = null;
        Position bestPosition = null;
//        ArrayList<Piece> pieces = new ArrayList<>(board.getPieces(team));
        ArrayList<Piece> pieces = board.getPieces(team);
        System.out.println(pieces);  // TODO: debug

        setTeam(team);
        // check every possible move and return the best of them
//        for (Piece piece : pieces) {
        for (int i = 0; i < pieces.size(); ++i) {
            Piece piece = pieces.get(i);
            System.out.println("Currently exploring " + piece + " of type: " + piece.getType());  // TODO: debug
            ArrayList<Position> possibleMoves = piece.getMoves();
            for (Position nextPosition : possibleMoves) {
                System.out.println("Current move: " + nextPosition);  // TODO: debug
                board.movePiece(piece, nextPosition);
                score = minimax(INITIAL_DEPTH, INITIAL_IS_MAXIMIZING, Helper.enemyTeam(team));
                if (score > maxScore) {
                    maxScore = score;
                    bestPiece = piece;
                    bestPosition = nextPosition;
                }
                // return board to initial state
                board.undoMove(piece);
            }
        }

        if (bestPiece != null) {
            nextMove = "move " + bestPiece.toStringPosition() + bestPosition.toString() + "\n";
            board.movePiece(bestPiece, bestPosition);
        } else {
            nextMove = "exit\n";
        }

//        board.printBoard();  // TODO: debug | remove later
        return nextMove;
    }

    public int minimax(int depth, boolean isMaximizing, String currentTeam) {
        // System.out.print("DEPTH: " + depth + " ");  // TODO: debug
        int bestScore, score;
//        ArrayList<Piece> pieces = new ArrayList<Piece>(board.getPieces(currentTeam));
        ArrayList<Piece> pieces = board.getPieces(currentTeam);
        // System.out.println(pieces); // TODO: debug
        King king = board.getKing(currentTeam);
        if (king != null) {
            if (depth == MAX_DEPTH || king.isCheckMate()) {
                // System.out.println(evaluate(this.team)); //TODO: debug
                return evaluate(this.team);
            }
        } else {
            return evaluate(this.team);
        }

        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
//            for (Piece piece : pieces) {
            for (int i = 0; i < pieces.size(); ++i) {
                Piece piece = pieces.get(i);
                 System.out.println("Currently exploring " + piece);  // TODO: debug
                ArrayList<Position> possibleMoves = piece.getMoves();
                for (Position mov : possibleMoves) {
                    System.out.print(mov + " ");
                }
                System.out.println();
//                board.printBoard();
                for (Position nextPos : possibleMoves) {
                    System.out.println("Current move: " + nextPos);  // TODO: debug
                    board.movePiece(piece, nextPos);
//                    board.printBoard();
                    score = minimax(depth + 1, !isMaximizing, Helper.enemyTeam(currentTeam));
                    System.out.println("score: " + score);
                    bestScore = Math.max(bestScore, score);
                    System.out.println("best score now:" + bestScore);
                    board.undoMove(piece);
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
//            for (Piece piece : pieces) {
            for (int i = 0; i < pieces.size(); ++i) {
                Piece piece = pieces.get(i);
                System.out.println("Currently exploring " + piece);  // TODO: debug
                ArrayList<Position> possibleMoves = piece.getMoves();
                for (Position mov : possibleMoves) {
                    System.out.print(mov + " ");
                }
                System.out.println();
                for (Position nextPos : possibleMoves) {
                     System.out.println("Current move: " + nextPos);  // TODO: debug
                    board.movePiece(piece, nextPos);
//                    board.printBoard();
                    score = minimax(depth + 1, !isMaximizing, Helper.enemyTeam(currentTeam));
                    System.out.println("score: " + score);
                    // TODO: AM SCHIMBAT AICI CU MATH.MAX;
                    bestScore = Math.min(bestScore, score);
                    System.out.println("best score now:" + bestScore);
                    board.undoMove(piece);
                }
            }
        }

        return bestScore;
    }

    public final int evaluate(String team) {
        if (team.equals("White")) {
            return evaluateForWhite() - evaluateForBlack();
        } else {
            return evaluateForBlack() - evaluateForWhite();
        }
    }

    public final int evaluateForWhite() {
        int sum = 0;

        for (Piece piece : board.getWhitePieces()) {
            sum += evaluatePiece(piece);
        }

        return sum;
    }

    public final int evaluateForBlack() {
        int sum = 0;

        for (Piece piece : board.getBlackPieces()) {
            sum += evaluatePiece(piece);
        }

        return sum;
    }

    public final int evaluatePiece(Piece piece) {
        String pieceType = piece.getType();
        switch (pieceType) {
            case "King": return KING_POINTS;
            case "Queen": return QUEEN_POINTS;
            case "Rook": return ROOK_POINTS;
            case "Bishop": return BISHOP_POINTS;
            case "Knight": return KNIGHT_POINTS;
            case "Pawn": return PAWN_POINTS;
            default : return 0;
        }
    }
}
