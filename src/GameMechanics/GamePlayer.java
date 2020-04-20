package GameMechanics;

import BoardGame.Board;
import ChessPieces.*;
import Helper.Helper;

import java.util.ArrayList;

public class GamePlayer {
    private static final int MAX_DEPTH = 5;
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
        ArrayList<Piece> pieces = board.getPieces(team);

        setTeam(team);
        // check every possible move and return the best of them
        for (Piece piece : pieces) {
            ArrayList<Position> possibleMoves = piece.getMoves();
            for (Position nextPosition : possibleMoves) {
                board.movePiece(piece, nextPosition);
                score = minimax(INITIAL_DEPTH, INITIAL_IS_MAXIMIZING, Helper.enemyTeam(team));
                if (score > maxScore) {
                    score = maxScore;
                    bestPiece = piece;
                    bestPosition = nextPosition;
                }
                // return board to initial state
                board.undoMove(piece);
            }
        }

        if (bestPiece != null) {
            nextMove = "move " + bestPiece.toStringPosition() + bestPosition.toString() + "\n";
        } else {
            nextMove = "exit\n";
        }

        board.printBoard();  // TODO: debug | remove later
        return nextMove;
    }

    public int minimax(int depth, boolean isMaximizing, String currentTeam) {
        int bestScore, score;
        ArrayList<Piece> pieces = board.getPieces(currentTeam);
        if (depth == MAX_DEPTH || board.getKing(currentTeam).isCheckMate()) {
            return evaluate(this.team);
        }

        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (Piece piece : pieces) {
                ArrayList<Position> possibleMoves = piece.getMoves();
                for (Position nextPos : possibleMoves) {
                    board.movePiece(piece, nextPos);
                    score = minimax(depth + 1, !isMaximizing, Helper.enemyTeam(currentTeam));
                    bestScore = Math.max(bestScore, score);
                    board.undoMove(piece);
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (Piece piece : pieces) {
                ArrayList<Position> possibleMoves = piece.getMoves();
                for (Position nextPos : possibleMoves) {
                    board.movePiece(piece, nextPos);
                    score = minimax(depth + 1, !isMaximizing, Helper.enemyTeam(currentTeam));
                    bestScore = Math.min(bestScore, score);
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
