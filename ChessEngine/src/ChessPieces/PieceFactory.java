package ChessPieces;

public final class PieceFactory {
    private static PieceFactory instance = null;

    private PieceFactory() { }

    public static PieceFactory getInstance() {
        if (instance == null) {
            instance = new PieceFactory();
        }
        return instance;
    }

    public Piece createPiece(final String type, final Integer line, final Integer column, final String color) {
        switch (type) {
            case "King": return new King(line, column, color);
            case "Queen": return new Queen(line, column, color);
            case "Rook": return new Rook(line, column, color);
            case "Bishop": return new Bishop(line, column, color);
            case "Knight": return new Knight(line, column, color);
            case "Pawn": return new Pawn(line, column, color);
            default : return null;
        }
    }
}
