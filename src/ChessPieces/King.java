package ChessPieces;

import java.util.ArrayList;

public final class King extends Piece {

    public King(final Integer line, final Integer column, final String color) {
        this.line = line;
        this.column = column;
        team = color;
        type = "King";
    }

    @Override
    public final boolean isCheckMate() {
        return false;
    }

    public ArrayList<Position> getMoves() {
        return null;
    }
}
