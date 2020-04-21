package GameMechanics;

import BoardGame.Board;
import BoardGame.Evaluator;
import ChessPieces.*;
import Helper.Helper;
import Helper.Constants;

import java.util.ArrayList;

public class GamePlayer {
<<<<<<< HEAD
    private static final int MAX_DEPTH = 4;
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
        for (int i = 0; i < pieces.size(); ++i) {
            Piece piece = pieces.get(i);
            if (piece.isAlive()) {
                ArrayList<Position> possibleMoves = piece.getMoves();
                for (Position nextPosition : possibleMoves) {
                    board.movePiece(piece, nextPosition);
                    score = minimax(INITIAL_DEPTH, INITIAL_IS_MAXIMIZING, Helper.enemyTeam(team));
                    // return board to initial state
                    board.undoMove();
                    if (score > maxScore) {
                        maxScore = score;
                        bestPiece = piece;
                        bestPosition = nextPosition;
                    }
                }
            }
        }

        if (bestPiece != null) {
            nextMove = "move " + bestPiece.toStringPosition() + bestPosition.toString() + "\n";
            board.movePiece(bestPiece, bestPosition);
        } else {
            nextMove = "quit\n";
        }

        board.printBoard();  // TODO: debug | remove later
        System.out.println("History is: " + board.getMoveHistory());
        return nextMove;
    }

    public int minimax(int depth, boolean isMaximizing, String currentTeam) {
        Evaluator evaluator = Evaluator.getInstance();
        int bestScore, score;
        ArrayList<Piece> pieces = new ArrayList<Piece>(board.getPieces(currentTeam));

        if (board.getKing(currentTeam).isCheckMate()) {
            if (currentTeam == this.team) {
                return -Constants.CHECK_MATE_POINTS;
            } else {
                return Constants.CHECK_MATE_POINTS;
            }
        }

        if (depth == MAX_DEPTH) {
            return evaluator.eval(this.team);
        }

        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < pieces.size(); ++i) {
                Piece piece = pieces.get(i);
                if (piece.isAlive()) {
                    ArrayList<Position> possibleMoves = piece.getMoves();
                    for (Position nextPos : possibleMoves) {
                        if (!piece.isOnBoard(nextPos.getLine(), nextPos.getColumn())) continue;
                        board.movePiece(piece, nextPos);
                        score = minimax(depth + 1, !isMaximizing, Helper.enemyTeam(currentTeam));
                        board.undoMove();
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < pieces.size(); ++i) {
                Piece piece = pieces.get(i);
                if (piece.isAlive()) {
                    // System.out.println("Currently exploring " + piece);  // TODO: debug
                    ArrayList<Position> possibleMoves = piece.getMoves();
                    for (Position nextPos : possibleMoves) {
                        // System.out.println("Current move: " + nextPos);  // TODO: debug
                        board.movePiece(piece, nextPos);
                        score = minimax(depth + 1, !isMaximizing, Helper.enemyTeam(currentTeam));
                        board.undoMove();
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
        }

        return bestScore;
    }
}
