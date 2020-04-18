package ChessPieces;

import BoardGame.Board;

import java.util.ArrayList;

public final class Rook extends Piece {

    public Rook(final Integer line, final Integer column, final String color) {
        this.line = line;
        this.column = column;
        team = color;
        type = "Rook";
    }
    public ArrayList<Position> getMoves() {
        Board board = Board.getInstance();
        return generateAllMovesByTeam(team, line, column, board);
    }

    private ArrayList<Position> generateAllMovesByTeam(final String team, final Integer line, final Integer column,
                                                       final Board board) {
        ArrayList<Position> moves = new ArrayList<>();

        for (int i = line + 1; i <= 8; ++i) {
            // move down
            if (board.isEmpty(i, column)) {
                moves.add(new Position(i, column));
            } else {
                if (!board.getPiece(i, column).getTeam().equals(team)) {
                    moves.add(new Position(i, column));
                }
                break;
            }
        }
        for (int i = line - 1; i >= 1; --i) {
            // move up
            if (board.isEmpty(i, column)) {
                moves.add(new Position(i, column));
            } else {
                if (!board.getPiece(i, column).getTeam().equals(team)) {
                    moves.add(new Position(i, column));
                }
                break;
            }
        }
        for (int i = column + 1; i <= 8; ++i) {
            // move right
            if (board.isEmpty(line, i)) {
                moves.add(new Position(line, i));
            } else {
                if (!board.getPiece(line, i).getTeam().equals(team)) {
                    moves.add(new Position(line, i));
                }
                break;
            }
        }
        for (int i = column - 1; i >= 1; --i) {
            // move left
            if (board.isEmpty(line, i)) {
                moves.add(new Position(line, i));
            } else {
                if (!board.getPiece(line, i).getTeam().equals(team)) {
                    moves.add(new Position(line, i));
                }
                break;
            }
        }

        return moves;
    }
}