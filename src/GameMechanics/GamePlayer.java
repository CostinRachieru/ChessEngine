package GameMechanics;

import BoardGame.Board;
import BoardGame.Evaluator;
import ChessPieces.*;
import Helper.Helper;

import java.util.ArrayList;

public class GamePlayer {
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
            int kk = board.getMoveHistory().size();
            System.out.print (board.getMoveHistory().size() + " - ");
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
            System.out.print (board.getMoveHistory().size() + " - ");
            int k = board.getMoveHistory().size();
            while (k > kk) {
                board.undoMove();
                k = board.getMoveHistory().size();
            }
            System.out.println(board.getMoveHistory().size());
        }

        if (bestPiece != null) {
            nextMove = "move " + bestPiece.toStringPosition() + bestPosition.toString() + "\n";
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
        ArrayList<Piece> pieces = board.getPieces(team);
        if (depth == MAX_DEPTH || board.getKing(currentTeam).isCheckMate()) {
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
                    ArrayList<Position> possibleMoves = piece.getMoves();
//                    for (Position nextPos : possibleMoves) {
                    for (int k = 0; k < possibleMoves.size(); ++k) {
                        Position nextPos = possibleMoves.get(k);
                        if (!piece.isOnBoard(nextPos.getLine(), nextPos.getColumn())) {
                            System.out.println("!@#!@#!@#!@#!@#");
                            System.out.println(piece.toString());
                            System.out.println(nextPos.toString());
                            System.out.println(possibleMoves.size());
                            continue;
                        }
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
