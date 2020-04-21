package Helper;

public final class Constants {
    private Constants() {
    }

    public static final int BLACK_FIRST_LINE = 8;
    public static final int RIGHT_COLUMN = 8;
    public static final int LEFT_COLUMN = 1;
    public static final int BOARD_SIZE = 8;

    public static final int PAWN_VALUE = 100;
    public static final int KNIGHT_VALUE = 320;
    public static final int BISHOP_VALUE = 325;
    public static final int ROOK_VALUE = 500;
    public static final int QUEEN_VALUE = 900;
    public static final int KING_VALUE = 0;
    public static final int CAN_CASTLE_BONUS = -30;
    public static final int HAS_CASTLED_BONUS = 30;
    public static final int END_GAME = 1350;
}