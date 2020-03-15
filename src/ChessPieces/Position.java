package ChessPieces;

public final class Position {
    private Integer line;

    private Integer column;

    public Position(Integer x, Integer y) {
        line = x;
        column = y;
    }

    public Position(String pos) {
        char c = pos.charAt(0);
        switch (c) {
            case 'a': column = 1; break;
            case 'b': column = 2; break;
            case 'c': column = 3; break;
            case 'd': column = 4; break;
            case 'e': column = 5; break;
            case 'f': column = 6; break;
            case 'g': column = 7; break;
            case 'h': column = 8; break;
            default : column = 0; break;
        }
        char d = pos.charAt(1);
        switch (d) {
            case '1': line = 1; break;
            case '2': line = 2; break;
            case '3': line = 3; break;
            case '4': line = 4; break;
            case '5': line = 5; break;
            case '6': line = 6; break;
            case '7': line = 7; break;
            case '8': line = 8; break;
            default : line = 0; break;
        }
    }

    public String toString() {
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
}
