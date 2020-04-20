package ChessPieces;

import java.util.ArrayList;

public abstract class Piece {
    protected String type;
    protected String team;
    protected Integer prevLine;
    protected Integer prevColumn;
    protected Integer line;
    protected Integer column;
    protected boolean hadMoved;

    public String toStringPosition() {
        String output = new String();
        switch (column) {
            case 1: output = "a"; break;
            case 2: output = "b"; break;
            case 3: output = "c"; break;
            case 4: output = "d"; break;
            case 5: output = "e"; break;
            case 6: output = "f"; break;
            case 7: output = "g"; break;
            case 8: output = "h"; break;
            default : return null;
        }
        output += line.toString();
        return output;
    }

    public void move(Position newPos) {
        prevLine = line;
        prevColumn = column;
        line = newPos.getLine();
        column = newPos.getColumn();
        if (hadMoved == false) {
            hadMoved = true;
        }
    }

    public boolean isOnBoard(int line, int column) {
        return line > 0 && line < 9 && column > 0 && column < 9;
    }

    public boolean isCheckMate() {
        return false;
    }

    public boolean movedTwoPawn() {
        return false;
    }

    public abstract ArrayList<Position> getMoves();

    public Integer getPrevLine() {
        return prevLine;
    }

    public void setPrevLine(Integer prevLine) {
        this.prevLine = prevLine;
    }

    public Integer getPrevColumn() {
        return prevColumn;
    }

    public void setPrevColumn(Integer prevColumn) {
        this.prevColumn = prevColumn;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getType() {
        return type;
    }

    public String getTeam() {
        return team;
    }

    public boolean getHadMoved() {
        return hadMoved;
    }

    @Override
    public String toString() {
        return type + " " + toStringPosition();
    }
}
