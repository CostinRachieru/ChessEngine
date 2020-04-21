package ChessPieces;

import BoardGame.Board;

import java.util.ArrayList;

import static Helper.Constants.BISHOP_VALUE;

public final class Bishop extends Piece {

    public Bishop(final Integer line, final Integer column, final String color) {
        value = BISHOP_VALUE;
        isAlive = true;
        this.line = line;
        this.column = column;
        team = color;
        type = "Bishop";
        hadMoved = false;
        prevColumn = new ArrayList<>();
        prevLine = new ArrayList<>();
    }
    public ArrayList<Position> getMoves() {
        Board board = Board.getInstance();
        ArrayList<Position> moves = new ArrayList<>();
        // Up - Left
        int newLine = line + 1;
        int newColumn = column - 1;
        while (isOnBoard(newLine, newColumn)) {
            if (board.isEmpty(newLine, newColumn)) {
                if (board.isMoveValid(this, new Position(newLine, newColumn))) {
                    moves.add(new Position(newLine, newColumn));
                }
            } else {
                if (!board.getPiece(newLine, newColumn).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(newLine, newColumn))) {
                        moves.add(new Position(newLine, newColumn));
                    }
                }
                break;
            }
            newLine++;
            newColumn--;
        }

        //Up - Right
        newLine = line + 1;
        newColumn = column + 1;
        while (isOnBoard(newLine, newColumn)) {
            if (board.isEmpty(newLine, newColumn)) {
                if (board.isMoveValid(this, new Position(newLine, newColumn))) {
                    moves.add(new Position(newLine, newColumn));
                }
            } else {
                if (!board.getPiece(newLine, newColumn).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(newLine, newColumn))) {
                        moves.add(new Position(newLine, newColumn));
                    }
                }
                break;
            }
            newLine++;
            newColumn++;
        }

        //Down - Left
        newLine = line - 1;
        newColumn = column - 1;
        while (isOnBoard(newLine, newColumn)) {
            if (board.isEmpty(newLine, newColumn)) {
                if (board.isMoveValid(this, new Position(newLine, newColumn))) {
                    moves.add(new Position(newLine, newColumn));
                }
            } else {
                if (!board.getPiece(newLine, newColumn).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(newLine, newColumn))) {
                        moves.add(new Position(newLine, newColumn));
                    }
                }
                break;
            }
            newLine--;
            newColumn--;
        }

        //Down - Right
        newLine = line - 1;
        newColumn = column + 1;
        while (isOnBoard(newLine, newColumn)) {
            if (board.isEmpty(newLine, newColumn)) {
                if (board.isMoveValid(this, new Position(newLine, newColumn))) {
                    moves.add(new Position(newLine, newColumn));
                }
            } else {
                if (!board.getPiece(newLine, newColumn).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(newLine, newColumn))) {
                        moves.add(new Position(newLine, newColumn));
                    }
                }
                break;
            }
            newLine--;
            newColumn++;
        }
        return moves;
    }
}
