package ChessPieces;

import BoardGame.Board;

import java.util.ArrayList;

public final class Pawn extends Piece {
    public boolean movedTwo;

    public Pawn(final Integer line, final Integer column, final String color) {
        this.line = line;
        this.column = column;
        team = color;
        type = "Pawn";
        movedTwo = false;
    }

    @Override
    public boolean movedTwoPawn() {
        return movedTwo;
    }

    @Override
    public final void move(Position newPos) {
        prevLine = line;
        prevColumn = column;
        line = newPos.getLine();
        column = newPos.getColumn();
        if (Math.abs(prevColumn - column) == 2) {
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
            if (board.isEmpty(newLine - 1, newColumn)) {
                newLine--;
                if (newLine > 0) {
                    moves.add(new Position(newLine, newColumn));
                }
                if (newLine > 1) {
                    if (board.isEmpty(newLine - 1, newColumn) && newLine + 1 == 7) {
                        newLine--;
                        moves.add(new Position(newLine, newColumn));
                    }
                }
            }
            newLine = line;
            newColumn = column;
            // Diagonala - Dreapta
            if (newColumn < 8 && newLine > 1) {
                if (!board.isEmpty(newLine - 1, newColumn + 1)) {
                    if (!board.getPiece(newLine - 1, newColumn + 1).getTeam().equals(team)) {
                        moves.add(new Position(newLine - 1, newColumn + 1));
                    }
                }
            }
            // Diagonala - Stanga
            if (newColumn > 1 && newLine > 1) {
                if (!board.isEmpty(newLine - 1, newColumn - 1)) {
                    if (!board.getPiece(newLine - 1, newColumn - 1).getTeam().equals(team)) {
                        moves.add(new Position(newLine - 1, newColumn - 1));
                    }
                }
            }
            // En-passant - stanga
            if (newColumn > 1 && newLine > 1) {
                if (!board.isEmpty(newLine, newColumn - 1)) {
                    if (!board.getPiece(newLine, newColumn - 1).getTeam().equals(team)) {
                        if (board.getPiece(newLine, newColumn - 1).movedTwoPawn()) {
                            moves.add(new Position(newLine - 1, newColumn - 1));
                        }
                    }
                }
            }
            // En-passant - dreapta
            if (newColumn < 8 && newLine > 1) {
                if (!board.isEmpty(newLine, newColumn + 1)) {
                    if (!board.getPiece(newLine, newColumn + 1).getTeam().equals(team)) {
                        if (board.getPiece(newLine, newColumn + 1).movedTwoPawn()) {
                            moves.add(new Position(newLine - 1, newColumn + 1));
                        }
                    }
                }
            }
            //Team is White
        } else {
            //Prima in fata.
            if (board.isEmpty(newLine + 1, newColumn)) {
                newLine++;
                if (newLine < 9) {
                    moves.add(new Position(newLine, newColumn));
                }
                if (newLine < 8) {
                    if (board.isEmpty(newLine + 1, newColumn) && newLine - 1 == 2) {
                        newLine++;
                        moves.add(new Position(newLine, newColumn));
                    }
                }
            }
            newLine = line;
            newColumn = column;
            // Diagonala - Dreapta
            if (newColumn < 8 && newLine < 8) {
                if (!board.isEmpty(newLine + 1, newColumn + 1)) {
                    if (!board.getPiece(newLine + 1, newColumn + 1).getTeam().equals(team)) {
                        moves.add(new Position(newLine + 1, newColumn + 1));
                    }
                }
            }
            // Diagonala - Stanga
            if (newColumn > 1 && newLine < 8) {
                if (!board.isEmpty(newLine + 1, newColumn - 1)) {
                    if (!board.getPiece(newLine + 1, newColumn - 1).getTeam().equals(team)) {
                        moves.add(new Position(newLine + 1, newColumn - 1));
                    }
                }
            }
            // En-passant - stanga
            if (newColumn > 1 && newLine < 8) {
                if (!board.isEmpty(newLine, newColumn - 1)) {
                    if (!board.getPiece(newLine, newColumn - 1).getTeam().equals(team)) {
                        if (board.getPiece(newLine, newColumn - 1).movedTwoPawn()) {
                            moves.add(new Position(newLine + 1, newColumn - 1));
                        }
                    }
                }
            }
            // En-passant - dreapta
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
