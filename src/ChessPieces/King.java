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

    private boolean checkPawn(final Integer line, final Integer column, final Board board) {
        if (team.equals("White")) {
            // check up-right
            if (isOnBoard(line - 1, column + 1)) {
                if (!board.getPiece(line - 1, column + 1).getTeam().equals(team)) {
                    if (board.getPiece(line - 1, column + 1).getType().equals("Pawn")) {
                        return true;
                    }
                }
            }
            // check up-left
            if (isOnBoard(line - 1, column - 1)) {
                if (!board.getPiece(line - 1, column - 1).getTeam().equals(team)) {
                    if (board.getPiece(line - 1, column - 1).getType().equals("Pawn")) {
                        return true;
                    }
                }
            }
        } else {
            // check down-right
            if (isOnBoard(line + 1, column + 1)) {
                if (!board.getPiece(line + 1, column + 1).getTeam().equals(team)) {
                    if (board.getPiece(line + 1, column + 1).getType().equals("Pawn")) {
                        return true;
                    }
                }
            }
            // check down-left
            if (isOnBoard(line + 1, column - 1)) {
                if (!board.getPiece(line + 1, column - 1).getTeam().equals(team)) {
                    if (board.getPiece(line + 1, column - 1).getType().equals("Pawn")) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean checkKing(final Integer line, final Integer column, final Board board) {
        // up-right
        if (isOnBoard(line - 1, column + 1)) {
            if (!board.getPiece(line - 1, column + 1).getTeam().equals(team)) {
                if (board.getPiece(line - 1, column + 1).getType().equals("King")) {
                    return true;
                }
            }
        }
        // up
        if (isOnBoard(line - 1, column)) {
            if (!board.getPiece(line - 1, column).getTeam().equals(team)) {
                if (board.getPiece(line - 1, column).getType().equals("King")) {
                    return true;
                }
            }
        }
        // up-left
        if (isOnBoard(line - 1, column - 1)) {
            if (!board.getPiece(line - 1, column - 1).getTeam().equals(team)) {
                if (board.getPiece(line - 1, column - 1).getType().equals("King")) {
                    return true;
                }
            }
        }
        // left
        if (isOnBoard(line, column - 1)) {
            if (!board.getPiece(line, column - 1).getTeam().equals(team)) {
                if (board.getPiece(line, column - 1).getType().equals("King")) {
                    return true;
                }
            }
        }
        // down-left
        if (isOnBoard(line + 1, column - 1)) {
            if (!board.getPiece(line + 1, column - 1).getTeam().equals(team)) {
                if (board.getPiece(line + 1, column - 1).getType().equals("King")) {
                    return true;
                }
            }
        }
        // down
        if (isOnBoard(line + 1, column)) {
            if (!board.getPiece(line + 1, column).getTeam().equals(team)) {
                if (board.getPiece(line - 1, column).getType().equals("King")) {
                    return true;
                }
            }
        }
        // down-right
        if (isOnBoard(line + 1, column + 1)) {
            if (!board.getPiece(line + 1, column + 1).getTeam().equals(team)) {
                if (board.getPiece(line + 1, column + 1).getType().equals("King")) {
                    return true;
                }
            }
        }
        // right
        if (isOnBoard(line, column + 1)) {
            if (!board.getPiece(line, column + 1).getTeam().equals(team)) {
                if (board.getPiece(line, column + 1).getType().equals("King")) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkKnight(final Integer line, final Integer column, final Board board) {
        if (isOnBoard(line - 2, column + 1)) {
            if (!board.getPiece(line - 2, column + 1).getTeam().equals(team)) {
                if (board.getPiece(line - 2, column + 1).getType().equals("Knight")) {
                    return true;
                }
            }
        }
        if (isOnBoard(line - 2, column - 1)) {
            if (!board.getPiece(line - 2, column - 1).getTeam().equals(team)) {
                if (board.getPiece(line - 2, column - 1).getType().equals("Knight")) {
                    return true;
                }
            }
        }
        if (isOnBoard(line + 2, column - 1)) {
            if (!board.getPiece(line + 2, column - 1).getTeam().equals(team)) {
                if (board.getPiece(line + 2, column - 1).getType().equals("Knight")) {
                    return true;
                }
            }
        }
        if (isOnBoard(line + 2, column + 1)) {
            if (!board.getPiece(line + 2, column + 1).getTeam().equals(team)) {
                if (board.getPiece(line + 2, column + 1).getType().equals("Knight")) {
                    return true;
                }
            }
        }
        if (isOnBoard(line - 1, column + 2)) {
            if (!board.getPiece(line - 1, column + 2).getTeam().equals(team)) {
                if (board.getPiece(line - 1, column + 2).getType().equals("Knight")) {
                    return true;
                }
            }
        }
        if (isOnBoard(line - 1, column - 2)) {
            if (!board.getPiece(line - 1, column - 2).getTeam().equals(team)) {
                if (board.getPiece(line - 1, column - 2).getType().equals("Knight")) {
                    return true;
                }
            }
        }
        if (isOnBoard(line + 1, column - 2)) {
            if (!board.getPiece(line + 1, column - 2).getTeam().equals(team)) {
                if (board.getPiece(line + 1, column - 2).getType().equals("Knight")) {
                    return true;
                }
            }
        }
        if (isOnBoard(line + 1, column + 2)) {
            if (!board.getPiece(line + 1, column + 2).getTeam().equals(team)) {
                if (board.getPiece(line + 1, column + 2).getType().equals("Knight")) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkBishopOrQueen(final Integer line, final Integer column, final Board board) {
        if (!board.getPiece(line, column).getTeam().equals(team)) {
            return board.getPiece(line, column).getType().equals("Bishop")
                    || board.getPiece(line, column).getType().equals("Queen");
        }

        return false;
    }

    private boolean checkRookOrQueen(final Integer line, final Integer column, final Board board) {
        if (!board.getPiece(line, column).getTeam().equals(team)) {
            return board.getPiece(line, column).getType().equals("Rook")
                    || board.getPiece(line, column).getType().equals("Queen");
        }

        return false;
    }

    public final boolean isCheck(final Integer line, final Integer column) {
        Board board = Board.getInstance();
        // check vertically-up
        for (int i = line - 1; i >= 1; --i) {
            if (!board.isEmpty(i, column)) {
                if (checkRookOrQueen(i, column, board)) {
                    return true;
                }
                break;
            }
        }
        // check vertically-down
        for (int i = line + 1; i<= 8; ++i) {
            if (!board.isEmpty(i, column)) {
                if (checkRookOrQueen(i, column, board)) {
                    return true;
                }
                break;
            }
        }
        // check horizontally-right
        for (int i = column + 1; i <= 8; ++i) {
            if (!board.isEmpty(line, i)) {
                if (checkRookOrQueen(line, i, board)) {
                    return true;
                }
                break;
            }
        }
        // check horizontally-left
        for (int i = column - 1; i >= 1; --i) {
            if (!board.isEmpty(line, i)) {
                if (checkRookOrQueen(line, i, board)) {
                    return true;
                }
                break;
            }
        }
        // check diagonal up-right
        for (int i = line - 1, j = column + 1; i >= 1 && j <= 8; --i, ++j) {
            if (!board.isEmpty(i, j)) {
                if (checkBishopOrQueen(i, j, board)) {
                    return true;
                }
                break;
            }
        }
        // check diagonal up-left
        for (int i = line - 1, j = column - 1; i >= 1 && j >= 1; --i, --j) {
            if (!board.isEmpty(i, j)) {
                if (checkBishopOrQueen(i, j, board)) {
                    return true;
                }
                break;
            }
        }
        // check diagonal down-left
        for (int i = line + 1, j = column - 1; i <= 8 && j >= 1; ++i, --j) {
            if (!board.isEmpty(i, j)) {
                if (checkBishopOrQueen(i, j, board)) {
                    return true;
                }
                break;
            }
        }
        // check diagonal down-right
        for (int i = line + 1, j = column + 1; i <= 8 && j <= 8; ++i, ++j) {
            if (!board.isEmpty(i, j)) {
                if (checkBishopOrQueen(i, j, board)) {
                    return true;
                }
                break;
            }
        }
        // check all threatening knight positions
        if (checkKnight(line, column, board)) {
            return true;
        }
        // check all threatening king positions
        if (checkKing(line, column, board)) {
            return true;
        }
        // check all threatening pawn positions
        if (checkPawn(line, column, board)) {
            return true;
        }

        return false;
    }

    public ArrayList<Position> getMoves() {
        Board board = Board.getInstance();
        ArrayList<Position> moves = new ArrayList<>();

        // Up - Left
        if (isOnBoard(line + 1, column - 1) && !isCheck(line + 1, column - 1)) {
            if (board.isEmpty(line + 1, column - 1)) {
                moves.add(new Position(line + 1, column - 1));
            } else {
                if (!board.getPiece(line + 1, column - 1).getTeam().equals(team)) {
                    moves.add(new Position(line + 1, column - 1));
                }
            }
        }

        // Up - Right
        if (isOnBoard(line + 1, column + 1) && !isCheck(line + 1, column + 1)) {
            if (board.isEmpty(line + 1, column + 1)) {
                moves.add(new Position(line + 1, column + 1));
            } else {
                if (!(board.getPiece(line + 1, column + 1).getTeam().equals(team)))
                    moves.add(new Position(line + 1, column + 1));
            }
        }

        // Down - Left
        if (isOnBoard(line - 1, column - 1) && !isCheck(line - 1, column - 1)) {
            if (board.isEmpty(line - 1, column - 1)) {
                moves.add(new Position(line - 1, column - 1));
            } else {
                if (!board.getPiece(line - 1, column - 1).getTeam().equals(team))
                    moves.add(new Position(line - 1, column - 1));
            }
        }

        // Down - Right
        if (isOnBoard(line - 1, column + 1) && !isCheck(line - 1, column + 1)) {
            if (board.isEmpty(line - 1, column + 1)) {
                moves.add(new Position(line - 1, column + 1));
            } else {
                if (!board.getPiece(line - 1, column + 1).getTeam().equals(team))
                    moves.add(new Position(line - 1, column + 1));
            }
        }

        // Up
        if (isOnBoard(line + 1, column) && !isCheck(line + 1, column)) {
            if (board.isEmpty(line + 1, column)) {
                moves.add(new Position(line + 1, column));
            } else {
                if (!board.getPiece(line + 1, column).getTeam().equals(team))
                    moves.add(new Position(line + 1, column));
            }
        }

        // Down
        if (isOnBoard(line - 1, column) && !isCheck(line - 1, column)) {
            if (board.isEmpty(line - 1, column)) {
                moves.add(new Position(line - 1, column));
            } else {
                if (!board.getPiece(line - 1, column).getTeam().equals(team))
                    moves.add(new Position(line - 1, column));
            }
        }

        // Left
        if (isOnBoard(line, column - 1) && !isCheck(line, column - 1)) {
            if (board.isEmpty(line, column - 1)) {
                moves.add(new Position(line, column - 1));
            } else {
                if (!board.getPiece(line, column - 1).getTeam().equals(team))
                    moves.add(new Position(line, column - 1));
            }
        }

        // Right
        if (isOnBoard(line, column + 1) && !isCheck(line, column + 1)) {
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
