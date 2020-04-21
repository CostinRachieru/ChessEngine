package ChessPieces;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Piece {
    protected Integer value;
    protected String type;
    protected String team;
    protected Integer line;
    protected Integer column;
    protected boolean hadMoved;
    protected LinkedList<PieceHistory> history;
    protected boolean isAlive = true;

    // Getters and Setters
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
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

    public boolean getHadMoved() {
        return hadMoved;
    }

    public void setHadMoved(boolean hadMoved) {
        this.hadMoved = hadMoved;
    }

    public LinkedList<PieceHistory> getHistory() {
        return history;
    }

    public void setHistory(LinkedList<PieceHistory> history) {
        this.history = history;
    }

    // Methods
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

    @Override
    public String toString() {
        return type + " " + toStringPosition();
    }

    public PieceHistory createHistoy() {
        return new PieceHistory(line, column, hadMoved);
    }
    public void addHistory(PieceHistory entry) {
        history.add(entry);
    }
    public PieceHistory getLastHistory() {
        PieceHistory pieceHistory = history.get(history.size() - 1);
        history.remove(history.size() - 1);
        return pieceHistory;
    }

    public void restoreToLastState(PieceHistory pieceHistory) {
        line = pieceHistory.getPrevLine();
        column = pieceHistory.getPrevCol();
        hadMoved = pieceHistory.hadPrevMoved();
    }

    public final Integer getValue() {
        return value;
    }
}
