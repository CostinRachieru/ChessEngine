package ChessPieces;

import java.util.ArrayList;

public final class Knight extends Piece {

    public Knight(final Integer line, final Integer column, final String color) {
        this.line = line;
        this.column = column;
        team = color;
        type = "Knight";
    }

    public ArrayList<Position> getMoves() {
        return null;
    }
}
