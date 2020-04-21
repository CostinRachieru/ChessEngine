package ChessPieces;

import BoardGame.Board;

import java.util.ArrayList;
import java.util.LinkedList;

import static Helper.Constants.KNIGHT_VALUE;

public final class Knight extends Piece {

    public Knight(final Integer line, final Integer column, final String color) {
        value = KNIGHT_VALUE;
        this.line = line;
        this.column = column;
        team = color;
        type = "Knight";
        hadMoved = false;
        history = new LinkedList<>();
    }

    public ArrayList<Position> getMoves() {
        Board board = Board.getInstance();
        ArrayList<Position> moves = new ArrayList<>();
        // move up-right
        if (isOnBoard(line + 2, column + 1)) {
            if (board.isEmpty(line + 2, column + 1)) {
                if (board.isMoveValid(this, new Position(line + 2, column + 1))) {
                    moves.add(new Position(line + 2, column + 1));
                }
            } else {
                if (!board.getPiece(line + 2, column + 1).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(line + 2, column + 1))) {
                        moves.add(new Position(line + 2, column + 1));
                    }
                }
            }
        }
        // move up-left
        if (isOnBoard(line + 2, column - 1)) {
            if (board.isEmpty(line + 2, column - 1)) {
                if (board.isMoveValid(this, new Position(line + 2, column - 1))) {
                    moves.add(new Position(line + 2, column - 1));
                }
            } else {
                if (!board.getPiece(line + 2, column - 1).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(line + 2, column - 1))) {
                        moves.add(new Position(line + 2, column - 1));
                    }
                }
            }
        }
        // move right-up
        if (isOnBoard(line + 1, column + 2)) {
            if (board.isEmpty(line + 1, column + 2)) {
                if (board.isMoveValid(this, new Position(line + 1, column + 2))) {
                    moves.add(new Position(line + 1, column + 2));
                }
            } else {
                if (!board.getPiece(line + 1, column + 2).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(line + 1, column + 2))) {
                        moves.add(new Position(line + 1, column + 2));
                    }
                }
            }
        }
        // move right-down
        if (isOnBoard(line - 1, column + 2)) {
            if (board.isEmpty(line - 1, column + 2)) {
                if (board.isMoveValid(this, new Position(line - 1, column + 2))) {
                    moves.add(new Position(line - 1, column + 2));
                }
            } else {
                if (!board.getPiece(line - 1, column + 2).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(line - 1, column + 2))) {
                        moves.add(new Position(line - 1, column + 2));
                    }
                }
            }
        }
        // move down-right
        if (isOnBoard(line - 2, column + 1)) {
            if (board.isEmpty(line - 2, column + 1)) {
                if (board.isMoveValid(this, new Position(line - 2, column + 1))) {
                    moves.add(new Position(line - 2, column + 1));
                }
            } else {
                if (!board.getPiece(line - 2, column + 1).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(line - 2, column + 1))) {
                        moves.add(new Position(line - 2, column + 1));
                    }
                }
            }
        }
        // move down-left
        if (isOnBoard(line - 2, column - 1)) {
            if (board.isEmpty(line - 2, column - 1)) {
                if (board.isMoveValid(this, new Position(line - 2, column - 1))) {
                    moves.add(new Position(line - 2, column - 1));
                }
            } else {
                if (!board.getPiece(line - 2, column - 1).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(line - 2, column - 1))) {
                        moves.add(new Position(line - 2, column - 1));
                    }
                }
            }
        }
        // move left-down
        if (isOnBoard(line - 1, column - 2)) {
            if (board.isEmpty(line - 1, column - 2)) {
                if (board.isMoveValid(this, new Position(line - 1, column - 2))) {
                    moves.add(new Position(line - 1, column - 2));
                }
            } else {
                if (!board.getPiece(line - 1, column - 2).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(line - 1, column -2))) {
                        moves.add(new Position(line - 1, column -2));
                    }
                }
            }
        }
        // move left-up
        if (isOnBoard(line + 1, column - 2)) {
            if (board.isEmpty(line + 1, column - 2)) {
                if (board.isMoveValid(this, new Position(line + 1, column - 2))) {
                    moves.add(new Position(line + 1, column - 2));
                }
            } else {
                if (!board.getPiece(line + 1, column - 2).getTeam().equals(team)) {
                    if (board.isMoveValid(this, new Position(line + 1, column - 2))) {
                        moves.add(new Position(line + 1, column - 2));
                    }
                }
            }
        }

        return moves;
    }
}
