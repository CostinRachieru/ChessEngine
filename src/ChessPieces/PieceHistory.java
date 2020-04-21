package ChessPieces;

public class PieceHistory {
    private Integer prevLine;
    private Integer prevCol;
    private boolean prevHadMoved;
    private boolean hasCaptured = false;
    private boolean wasCastling = false;
    private Piece capturedPiece = null;

    // Constructors
    public PieceHistory(Integer prevLine, Integer prevCol, boolean prevHadMoved, boolean hasCaptured, Piece capturedPiece) {
        this.prevLine = prevLine;
        this.prevCol = prevCol;
        this.prevHadMoved = prevHadMoved;
        this.hasCaptured = hasCaptured;
        this.capturedPiece = capturedPiece;
    }

    public PieceHistory(Integer prevLine, Integer prevCol, boolean prevHadMoved) {
        this.prevLine = prevLine;
        this.prevCol = prevCol;
        this.prevHadMoved = prevHadMoved;
    }

    // Getters and Setter
    public boolean wasCastling() {
        return wasCastling;
    }

    public void setWasCastling(boolean wasCastling) {
        this.wasCastling = wasCastling;
    }

    public Integer getPrevLine() {
        return prevLine;
    }

    public void setPrevLine(Integer prevLine) {
        this.prevLine = prevLine;
    }

    public Integer getPrevCol() {
        return prevCol;
    }

    public void setPrevCol(Integer prevCol) {
        this.prevCol = prevCol;
    }

    public boolean hadPrevMoved() {
        return prevHadMoved;
    }

    public void setPrevHadMoved(boolean prevHadMoved) {
        this.prevHadMoved = prevHadMoved;
    }

    public boolean hasCaptured() {
        return hasCaptured;
    }

    public void setHasCaptured(boolean hasCaptured) {
        this.hasCaptured = hasCaptured;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public void setCapturedPiece(Piece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }

    // Methods
}
