package GameMechanics;

import BoardGame.Board;
import ChessPieces.Piece;
import ChessPieces.Position;
import Helper.Helper;

import java.util.ArrayList;

public class GamePlayer {
    private static final int MAX_DEPTH = 5;
    private static final int INITIAL_DEPTH = 1;
    private static final boolean INITIAL_IS_MAXIMIZING = false;

    private Board board;

    public GamePlayer(Board board) {
        this.board = board;
    }

    // function returns a string representing the next move as a XBoard command
    public String playTurn(String team) {
        int score, maxScore = Integer.MIN_VALUE;
        String nextMove;
        Piece bestPiece = null;
        Position bestPosition = null;
        ArrayList<Piece> pieces = board.getPieces(team);

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

        return nextMove;
    }

    public int minimax(int depth, boolean isMaximizing, String currentTeam) {
        int bestScore, score;
        ArrayList<Piece> pieces = board.getPieces(currentTeam);
        if (depth == MAX_DEPTH || board.getKing(currentTeam).isCheckMate()) {
            return evaluate();
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

    // TODO: evaluate function
    public final int evaluate() {
        return 0;
    }
}
