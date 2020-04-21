package ChessPieces;

import BoardGame.Board;

import java.util.ArrayList;
import java.util.LinkedList;

import static Helper.Constants.PAWN_VALUE;

public final class Pawn extends Piece {
    public boolean movedTwo;

    public Pawn(final Integer line, final Integer column, final String color) {
        value = PAWN_VALUE;
        this.line = line;
        this.column = column;
        team = color;
        type = "Pawn";
        movedTwo = false;
        hadMoved = false;
        history = new LinkedList<>();
    }

    @Override
    public boolean movedTwoPawn() {
        return movedTwo;
    }

    @Override
    public final void move(Position newPos) {
        int dist = Math.abs(newPos.getColumn() - this.column);
        super.move(newPos);
        if (dist == 2) {
            movedTwo = true;
        } else {
            movedTwo = false;
        }
    }

    @Override
    public ArrayList<Position> getMoves() {
        Board board = Board.getInstance();
        ArrayList<Position> moves = new ArrayList<>();
        Integer newLine = line;
        Integer newColumn = column;
        if (team.equals("Black")) {
            // First forward
            if (board.isEmpty(newLine - 1, newColumn)) {
                newLine--;
                if (newLine > 0) {
                    moves.add(new Position(newLine, newColumn));
                }
                // Two forward.
                if (newLine > 1) {
                    if (board.isEmpty(newLine - 1, newColumn) && newLine + 1 == 7) {
                        newLine--;
                        moves.add(new Position(newLine, newColumn));
                    }
                }
            }
            newLine = line;
            newColumn = column;
            // Diagonally - Right
            if (newColumn < 8 && newLine > 1) {
                if (!board.isEmpty(newLine - 1, newColumn + 1)) {
                    if (!board.getPiece(newLine - 1, newColumn + 1).getTeam().equals(team)) {
                        moves.add(new Position(newLine - 1, newColumn + 1));
                    }
                }
            }
            // Diagonally - Left
            if (newColumn > 1 && newLine > 1) {
                if (!board.isEmpty(newLine - 1, newColumn - 1)) {
                    if (!board.getPiece(newLine - 1, newColumn - 1).getTeam().equals(team)) {
                        moves.add(new Position(newLine - 1, newColumn - 1));
                    }
                }
            }
            // En-passant - Left
            if (newColumn > 1 && newLine > 1) {
                if (!board.isEmpty(newLine, newColumn - 1)) {
                    if (!board.getPiece(newLine, newColumn - 1).getTeam().equals(team)) {
                        if (board.getPiece(newLine, newColumn - 1).movedTwoPawn()) {
                            moves.add(new Position(newLine - 1, newColumn - 1));
                        }
                    }
                }
            }
            // En-passant - Right
            if (newColumn < 8 && newLine > 1) {
                if (!board.isEmpty(newLine, newColumn + 1)) {
                    if (!board.getPiece(newLine, newColumn + 1).getTeam().equals(team)) {
                        if (board.getPiece(newLine, newColumn + 1).movedTwoPawn()) {
                            moves.add(new Position(newLine - 1, newColumn + 1));
                        }
                    }
                }
            }
            // Team is White.
        } else {
            // First forward.
            if (board.isEmpty(newLine + 1, newColumn)) {
                newLine++;
                if (newLine < 9) {
                    moves.add(new Position(newLine, newColumn));
                }
                // Two forward.
                if (newLine < 8) {
                    if (board.isEmpty(newLine + 1, newColumn) && newLine - 1 == 2) {
                        newLine++;
                        moves.add(new Position(newLine, newColumn));
                    }
                }
            }
            newLine = line;
            newColumn = column;
            // Diagonally - Right
            if (newColumn < 8 && newLine < 8) {
                if (!board.isEmpty(newLine + 1, newColumn + 1)) {
                    if (!board.getPiece(newLine + 1, newColumn + 1).getTeam().equals(team)) {
                        moves.add(new Position(newLine + 1, newColumn + 1));
                    }
                }
            }
            // Diagonally - Left
            if (newColumn > 1 && newLine < 8) {
                if (!board.isEmpty(newLine + 1, newColumn - 1)) {
                    if (!board.getPiece(newLine + 1, newColumn - 1).getTeam().equals(team)) {
                        moves.add(new Position(newLine + 1, newColumn - 1));
                    }
                }
            }
            // En-passant - Left
            if (newColumn > 1 && newLine < 8) {
                if (!board.isEmpty(newLine, newColumn - 1)) {
                    if (!board.getPiece(newLine, newColumn - 1).getTeam().equals(team)) {
                        if (board.getPiece(newLine, newColumn - 1).movedTwoPawn()) {
                            moves.add(new Position(newLine + 1, newColumn - 1));
                        }
                    }
                }
            }
            // En-passant - Right
            if (newColumn < 8 && newLine < 8) {
                if (!board.isEmpty(newLine, newColumn + 1)) {
                    if (!board.getPiece(newLine, newColumn + 1).getTeam().equals(team)) {
                        if (board.getPiece(newLine, newColumn + 1).movedTwoPawn()) {
                            moves.add(new Position(newLine + 1, newColumn + 1));
                        }
                    }
                }
            }
        }
        return moves;
    }
}
