package GameMechanics;

import BoardGame.Board;
import BoardGame.Evaluator;
import ChessPieces.*;
import Helper.Helper;

import java.util.ArrayList;

public class GamePlayer {
    private static final int MAX_DEPTH = 2;
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
        ArrayList<Piece> pieces = new ArrayList<>(board.getPieces(team));

        setTeam(team);
        // check every possible move and return the best of them
        for (Piece piece : pieces) {
            if (piece.isAlive()) {
                System.out.println("Currently exploring " + piece);  // TODO: debug
                ArrayList<Position> possibleMoves = piece.getMoves();
                for (Position nextPosition : possibleMoves) {
                    System.out.println("Current move: " + nextPosition);  // TODO: debug
                    board.movePiece(piece, nextPosition);
                    score = minimax(INITIAL_DEPTH, INITIAL_IS_MAXIMIZING, Helper.enemyTeam(team));
                    System.out.println("Score is: " + score);
                    if (score > maxScore) {
                        maxScore = score;
                        bestPiece = piece;
                        bestPosition = nextPosition;
                    }
                    // return board to initial state
                    board.undoMove();
                }
            }
        }

        if (bestPiece != null) {
            nextMove = "move " + bestPiece.toStringPosition() + bestPosition.toString() + "\n";
            System.out.println("Best move is: " + bestPiece + " at " + bestPosition);
            board.movePiece(bestPiece, bestPosition);
        } else {
            nextMove = "quit\n";
        }

        board.printBoard();  // TODO: debug | remove later
        return nextMove;
    }

    public int minimax(int depth, boolean isMaximizing, String currentTeam) {
        // System.out.print("DEPTH: " + depth + " ");  // TODO: debug
        Evaluator evaluator = Evaluator.getInstance();
        int bestScore, score;
        ArrayList<Piece> pieces = new ArrayList<Piece>(board.getPieces(currentTeam));
        if (depth == MAX_DEPTH) /*|| board.getKing(currentTeam).isCheckMate())*/ {
            return evaluator.eval(this.team);
        }

        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (Piece piece : pieces) {
                if (piece.isAlive()) {
                    // System.out.println("Currently exploring " + piece);  // TODO: debug
                    ArrayList<Position> possibleMoves = piece.getMoves();
                    for (Position nextPos : possibleMoves) {
                        // System.out.println("Current move: " + nextPos);  // TODO: debug
                        board.movePiece(piece, nextPos);
                        score = minimax(depth + 1, !isMaximizing, Helper.enemyTeam(currentTeam));
                        bestScore = Math.max(bestScore, score);
                        board.undoMove();
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (Piece piece : pieces) {
                if (piece.isAlive()) {
                    // System.out.println("Currently exploring " + piece);  // TODO: debug
                    ArrayList<Position> possibleMoves = piece.getMoves();
                    for (Position nextPos : possibleMoves) {
                        // System.out.println("Current move: " + nextPos);  // TODO: debug
                        board.movePiece(piece, nextPos);
                        score = minimax(depth + 1, !isMaximizing, Helper.enemyTeam(currentTeam));
                        bestScore = Math.min(bestScore, score);
                        board.undoMove();
                    }
                }
            }
        }

        return bestScore;
    }
}
