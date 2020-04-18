package ChessPieces;

import BoardGame.Board;

import java.util.ArrayList;

public final class King extends Piece {

    public King(final Integer line, final Integer column, final String color) {
        this.line = line;
        this.column = column;
        team = color;
        type = "King";
    }

    @Override
    public final boolean isCheckMate() {
        return false;
    }

    public final boolean isCheckMate(final Integer line, final Integer column) {
        return false;
    }

    public ArrayList<Position> getMoves() {
        Board board = Board.getInstance();
        ArrayList<Position> moves = new ArrayList<>();

        // Up - Left
        if (isOnBoard(line + 1, column - 1) && !isCheckMate(line + 1, column - 1)) {
            if (board.isEmpty(line + 1, column - 1)) {
                moves.add(new Position(line + 1, column - 1));
            } else {
                if (!board.getPiece(line + 1, column - 1).getTeam().equals(team)) {
                    moves.add(new Position(line + 1, column - 1));
                }
            }
        }

        // Up - Right
        if (isOnBoard(line + 1, column + 1) && !isCheckMate(line + 1, column + 1)) {
            if (board.isEmpty(line + 1, column + 1)) {
                moves.add(new Position(line + 1, column + 1));
            } else {
                if (!(board.getPiece(line + 1, column + 1).getTeam().equals(team)))
                    moves.add(new Position(line + 1, column + 1));
            }
        }

        // Down - Left
        if (isOnBoard(line - 1, column - 1) && !isCheckMate(line - 1, column - 1)) {
            if (board.isEmpty(line - 1, column - 1)) {
                moves.add(new Position(line - 1, column - 1));
            } else {
                if (!board.getPiece(line - 1, column - 1).getTeam().equals(team))
                    moves.add(new Position(line - 1, column - 1));
            }
        }

        // Down - Right
        if (isOnBoard(line - 1, column + 1) && !isCheckMate(line - 1, column + 1)) {
            if (board.isEmpty(line - 1, column + 1)) {
                moves.add(new Position(line - 1, column + 1));
            } else {
                if (!board.getPiece(line - 1, column + 1).getTeam().equals(team))
                    moves.add(new Position(line - 1, column + 1));
            }
        }

        // Up
        if (isOnBoard(line + 1, column) && !isCheckMate(line + 1, column)) {
            if (board.isEmpty(line + 1, column)) {
                moves.add(new Position(line + 1, column));
            } else {
                if (!board.getPiece(line + 1, column).getTeam().equals(team))
                    moves.add(new Position(line + 1, column));
            }
        }

        // Down
        if (isOnBoard(line - 1, column) && !isCheckMate(line - 1, column)) {
            if (board.isEmpty(line - 1, column)) {
                moves.add(new Position(line - 1, column));
            } else {
                if (!board.getPiece(line - 1, column).getTeam().equals(team))
                    moves.add(new Position(line - 1, column));
            }
        }

        // Left
        if (isOnBoard(line, column - 1) && !isCheckMate(line, column - 1)) {
            if (board.isEmpty(line, column - 1)) {
                moves.add(new Position(line, column - 1));
            } else {
                if (!board.getPiece(line, column - 1).getTeam().equals(team))
                    moves.add(new Position(line, column - 1));
            }
        }

        // Right
        if (isOnBoard(line, column + 1) && !isCheckMate(line, column + 1)) {
            if (board.isEmpty(line, column + 1)) {
                moves.add(new Position(line, column + 1));
            } else {
                if (!board.getPiece(line, column + 1).getTeam().equals(team))
                    moves.add(new Position(line, column + 1));
            }
        }
        return moves;
    }
}
