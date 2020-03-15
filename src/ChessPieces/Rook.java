package ChessPieces;

import java.util.ArrayList;

public final class Rook extends Piece {

    public Rook(final Integer line, final Integer column, final String color) {
        this.line = line;
        this.column = column;
        team = color;
        type = "Rook";
    }
    public ArrayList<Position> getMoves() {
        return null;
    }
}
