package ChessPieces;

import BoardGame.Board;

import java.util.ArrayList;

public final class Queen extends Piece {

    public Queen(final Integer line, final Integer column, final String color) {
        this.line = line;
        this.column = column;
        team = color;
        type = "Queen";
    }
    public ArrayList<Position> getMoves() {
        Board board = Board.getInstance();
        ArrayList<Position> moves = generateBishopLikeMovesByTeam(team, line, column, board);
        moves.addAll(generateRookLikeMovesByTeam(team, line, column, board));

        return moves;
    }

    private ArrayList<Position> generateBishopLikeMovesByTeam(final String team, final Integer line, final Integer column,
                                                              final Board board) {
        ArrayList<Position> moves = new ArrayList<>();
        // up-right
        for (int i = line - 1, j = column + 1; i >= 1 && j <= 8; --i, ++j) {
            if (board.isEmpty(i, j)) {
                moves.add((new Position(i, j)));
            } else {
                if (!board.getPiece(i, j).getTeam().equals(team)) {
                    moves.add(new Position(i, j));
                }
                break;
            }
        }
        // up-left
        for (int i = line - 1, j = column - 1; i >= 1 && j >= 1; --i, --j) {
            if (board.isEmpty(i, j)) {
                moves.add((new Position(i, j)));
            } else {
                if (!board.getPiece(i, j).getTeam().equals(team)) {
                    moves.add(new Position(i, j));
                }
                break;
            }
        }
        // down-left
        for (int i = line + 1, j = column - 1; i <= 8 && j >= 1; ++i, --j) {
            if (board.isEmpty(i, j)) {
                moves.add((new Position(i, j)));
            } else {
                if (!board.getPiece(i, j).getTeam().equals(team)) {
                    moves.add(new Position(i, j));
                }
                break;
            }
        }
        // move-right
        for (int i = line + 1, j = column + 1; i <= 8 && j <= 8; ++i, ++j) {
            if (board.isEmpty(i, j)) {
                moves.add((new Position(i, j)));
            } else {
                if (!board.getPiece(i, j).getTeam().equals(team)) {
                    moves.add(new Position(i, j));
                }
                break;
            }
        }

        return moves;
    }

    private ArrayList<Position> generateRookLikeMovesByTeam(final String team, final Integer line, final Integer column,
                                                       final Board board) {
        ArrayList<Position> moves = new ArrayList<>();
        // move down
        for (int i = line + 1; i <= 8; ++i) {
            if (board.isEmpty(i, column)) {
                moves.add(new Position(i, column));
            } else {
                if (!board.getPiece(i, column).getTeam().equals(team)) {
                    moves.add(new Position(i, column));
                }
                break;
            }
        }
        // move up
        for (int i = line - 1; i >= 1; --i) {
            if (board.isEmpty(i, column)) {
                moves.add(new Position(i, column));
            } else {
                if (!board.getPiece(i, column).getTeam().equals(team)) {
                    moves.add(new Position(i, column));
                }
                break;
            }
        }
        // move right
        for (int i = column + 1; i <= 8; ++i) {
            if (board.isEmpty(line, i)) {
                moves.add(new Position(line, i));
            } else {
                if (!board.getPiece(line, i).getTeam().equals(team)) {
                    moves.add(new Position(line, i));
                }
                break;
            }
        }
        // move left
        for (int i = column - 1; i >= 1; --i) {
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
