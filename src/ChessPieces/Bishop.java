package ChessPieces;

import java.util.ArrayList;

public final class Bishop extends Piece {

    public Bishop(final Integer line, final Integer column, final String color) {
        this.line = line;
        this.column = column;
        team = color;
        type = "Bishop";
    }
    public ArrayList<Position> getMoves() {
        return null;
    }
}
