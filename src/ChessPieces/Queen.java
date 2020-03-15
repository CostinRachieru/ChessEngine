package ChessPieces;

import java.util.ArrayList;

public final class Queen extends Piece {

    public Queen(final Integer line, final Integer column, final String color) {
        this.line = line;
        this.column = column;
        team = color;
        type = "Queen";
    }
    public ArrayList<Position> getMoves() {
        return null;
    }
}
