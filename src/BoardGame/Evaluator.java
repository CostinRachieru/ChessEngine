package BoardGame;

import ChessPieces.King;
import ChessPieces.Piece;
import Helper.Constants;

import java.util.ArrayList;

public class Evaluator {
    private static Evaluator instance = null;

    private static final int[] pawnSquareBlack = {
            0,  0,  0,  0,  0,  0,  0,  0,
            50, 50, 50, 50, 50, 50, 50, 50,
            10, 10, 20, 30, 30, 20, 10, 10,
            5,  5, 10, 25, 25, 10,  5,  5,
            0,  0,  0, 20, 20,  0,  0,  0,
            5, -5,-10,  0,  0,-10, -5,  5,
            5, 10, 10,-20,-20, 10, 10,  5,
            0,  0,  0,  0,  0,  0,  0,  0
    };

    private static final int[] pawnSquareWhite = {
            0,  0,  0,  0,  0,  0,  0,  0,
            5, 10, 10,-20,-20, 10, 10,  5,
            5, -5,-10,  0,  0,-10, -5,  5,
            0,  0,  0, 20, 20,  0,  0,  0,
            5,  5, 10, 25, 25, 10,  5,  5,
            10, 10, 20, 30, 30, 20, 10, 10,
            50, 50, 50, 50, 50, 50, 50, 50,
            0,  0,  0,  0,  0,  0,  0,  0
    };

    private static final int[] knightSquareBlack = {
            -50,-40,-30,-30,-30,-30,-40,-50,
            -40,-20,  0,  0,  0,  0,-20,-40,
            -30,  0, 10, 15, 15, 10,  0,-30,
            -30,  5, 15, 20, 20, 15,  5,-30,
            -30,  0, 15, 20, 20, 15,  0,-30,
            -30,  5, 10, 15, 15, 10,  5,-30,
            -40,-20,  0,  5,  5,  0,-20,-40,
            -50,-40,-30,-30,-30,-30,-40,-50,
    };

    private static final int[] knightSquareWhite = {
            -50,-40,-30,-30,-30,-30,-40,-50,
            -40,-20,  0,  5,  5,  0,-20,-40,
            -30,  5, 10, 15, 15, 10,  5,-30,
            -30,  0, 15, 20, 20, 15,  0,-30,
            -30,  5, 15, 20, 20, 15,  5,-30,
            -30,  0, 10, 15, 15, 10,  0,-30,
            -40,-20,  0,  0,  0,  0,-20,-40,
            -50,-40,-30,-30,-30,-30,-40,-50,
    };

    private static final int[] bishopSquareBlack = {
            -20,-10,-10,-10,-10,-10,-10,-20,
            -10,  0,  0,  0,  0,  0,  0,-10,
            -10,  0,  5, 10, 10,  5,  0,-10,
            -10,  5,  5, 10, 10,  5,  5,-10,
            -10,  0, 10, 10, 10, 10,  0,-10,
            -10, 10, 10, 10, 10, 10, 10,-10,
            -10,  5,  0,  0,  0,  0,  5,-10,
            -20,-10,-10,-10,-10,-10,-10,-20,
    };

    private static final int[] bishopSquareWhite = {
            -20,-10,-10,-10,-10,-10,-10,-20,
            -10,  5,  0,  0,  0,  0,  5,-10,
            -10, 10, 10, 10, 10, 10, 10,-10,
            -10,  0, 10, 10, 10, 10,  0,-10,
            -10,  5,  5, 10, 10,  5,  5,-10,
            -10,  0,  5, 10, 10,  5,  0,-10,
            -10,  0,  0,  0,  0,  0,  0,-10,
            -20,-10,-10,-10,-10,-10,-10,-20,
    };

    private static final int[] rookSquareBlack = {
            0,  0,  0,  0,  0,  0,  0,  0,
            5, 10, 10, 10, 10, 10, 10,  5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            0,  0,  0,  5,  5,  0,  0,  0
    };

    private static final int[] rookSquareWhite = {
            0,  0,  0,  5,  5,  0,  0,  0 ,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            5, 10, 10, 10, 10, 10, 10,  5,
            0,  0,  0,  0,  0,  0,  0,  0,
    };

    private static final int[] queenSquareBlack = {
            -20,-10,-10, -5, -5,-10,-10,-20,
            -10,  0,  0,  0,  0,  0,  0,-10,
            -10,  0,  5,  5,  5,  5,  0,-10,
            -5,  0,  5,  5,  5,  5,  0, -5,
            0,  0,  5,  5,  5,  5,  0, -5,
            -10,  5,  5,  5,  5,  5,  0,-10,
            -10,  0,  5,  0,  0,  0,  0,-10,
            -20,-10,-10, -5, -5,-10,-10,-20
    };

    private static final int[] queenSquareWhite = {
            -20,-10,-10, -5, -5,-10,-10,-20,
            -10,  0,  5,  0,  0,  0,  0,-10,
            -10,  5,  5,  5,  5,  5,  0,-10,
            0,  0,  5,  5,  5,  5,  0, -5,
            -5,  0,  5,  5,  5,  5,  0, -5,
            -10,  0,  5,  5,  5,  5,  0,-10,
            -10,  0,  0,  0,  0,  0,  0,-10,
            -20,-10,-10, -5, -5,-10,-10,-20,
    };

    private static final int[] kingSquareOpeningBlack = {
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -20,-30,-30,-40,-40,-30,-30,-20,
            -10,-20,-20,-20,-20,-20,-20,-10,
            20, 20,  0,  0,  0,  0, 20, 20,
            20, 30, 10,  0,  0, 10, 30, 20
    };

    private static final int[] kingSquareOpeningWhite = {
            20, 30, 10,  0,  0, 10, 30, 20,
            20, 20,  0,  0,  0,  0, 20, 20,
            -10,-20,-20,-20,-20,-20,-20,-10,
            -20,-30,-30,-40,-40,-30,-30,-20,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
    };

    private static final int[] kingSquareEndGameBlack = {
            -50,-40,-30,-20,-20,-30,-40,-50,
            -30,-20,-10,  0,  0,-10,-20,-30,
            -30,-10, 20, 30, 30, 20,-10,-30,
            -30,-10, 30, 40, 40, 30,-10,-30,
            -30,-10, 30, 40, 40, 30,-10,-30,
            -30,-10, 20, 30, 30, 20,-10,-30,
            -30,-30,  0,  0,  0,  0,-30,-30,
            -50,-30,-30,-30,-30,-30,-30,-50
    };

    private static final int[] kingSquareEndGameWhite = {
            -50,-30,-30,-30,-30,-30,-30,-50,
            -30,-30,  0,  0,  0,  0,-30,-30,
            -30,-10, 20, 30, 30, 20,-10,-30,
            -30,-10, 30, 40, 40, 30,-10,-30,
            -30,-10, 30, 40, 40, 30,-10,-30,
            -30,-10, 20, 30, 30, 20,-10,-30,
            -30,-20,-10,  0,  0,-10,-20,-30,
            -50,-40,-30,-20,-20,-30,-40,-50,
    };

    private Evaluator() { }

    public static Evaluator getInstance() {
        if (instance == null) {
            instance = new Evaluator();
        }
        return instance;
    }

    public final Integer calculateMaterial(final String team) {
        Board board = Board.getInstance();
        ArrayList<Piece> pieces = board.getPieces(team);
        Integer material = 0;
        for (Piece piece : pieces) {
            if (!piece.isAlive()) {
                continue;
            }
            material += piece.getValue();
        }

        return material;
    }

    private boolean isEndgame() {
        return calculateMaterial("White") < Constants.END_GAME
                && calculateMaterial("Black") < Constants.END_GAME;
    }

    public final Integer eval(final String team) {
        Board board = Board.getInstance();
        Integer whiteScore = 0;
        Integer blackScore = 0;

        for (int i = 0; i < Constants.BOARD_SIZE; ++i) {
            for (int j = 0; j < Constants.BOARD_SIZE; ++j) {
                if (!board.isEmpty(i + 1, j + 1) && board.getPiece(i + 1, j + 1).isAlive()) {
                    switch (board.pieceSymbol(board.getPiece(i + 1, j + 1))) {
                        case 'P':
                            whiteScore += Constants.PAWN_VALUE;
                            whiteScore += pawnSquareWhite[i * Constants.BOARD_SIZE + j];
                            break;
                        case 'N':
                            whiteScore += Constants.KNIGHT_VALUE;
                            whiteScore += knightSquareWhite[i * Constants.BOARD_SIZE + j];
                            break;
                        case 'B':
                            whiteScore += Constants.BISHOP_VALUE;
                            whiteScore += bishopSquareWhite[i * Constants.BOARD_SIZE + j];
                            break;
                        case 'R':
                            whiteScore += Constants.ROOK_VALUE;
                            whiteScore += rookSquareWhite[i * Constants.BOARD_SIZE + j];
                            break;
                        case 'Q':
                            whiteScore += Constants.QUEEN_VALUE;
                            whiteScore += queenSquareWhite[i * Constants.BOARD_SIZE + j];
                            break;
                        case 'K':
                            if (isEndgame()) {
                                whiteScore += kingSquareEndGameWhite[i * Constants.BOARD_SIZE + j];
                            } else {
                                whiteScore += kingSquareOpeningWhite[i * Constants.BOARD_SIZE + j];
                            }
                            break;
                        case 'p':
                            blackScore += Constants.PAWN_VALUE;
                            blackScore += pawnSquareBlack[i * Constants.BOARD_SIZE + j];
                            break;
                        case 'n':
                            blackScore += Constants.KNIGHT_VALUE;
                            blackScore += knightSquareBlack[i * Constants.BOARD_SIZE + j];
                            break;
                        case 'b':
                            blackScore += Constants.BISHOP_VALUE;
                            blackScore += bishopSquareBlack[i * Constants.BOARD_SIZE + j];
                            break;
                        case 'r':
                            blackScore += Constants.ROOK_VALUE;
                            blackScore += rookSquareBlack[i * Constants.BOARD_SIZE + j];
                            break;
                        case 'q':
                            blackScore += Constants.QUEEN_VALUE;
                            blackScore += queenSquareBlack[i * Constants.BOARD_SIZE + j];
                            break;
                        case 'k':
                            if (isEndgame()) {
                                blackScore += kingSquareEndGameBlack[i * Constants.BOARD_SIZE + j];
                            } else {
                                blackScore += kingSquareOpeningBlack[i * Constants.BOARD_SIZE + j];
                            }
                            break;
                    }
                }
            }
        }
//            if (board.canCastle(team)) {
//                whiteScore += Constants.CAN_CASTLE_BONUS;
//            }


//            if (board.canCastle(team)) {
//                blackScore += Constants.CAN_CASTLE_BONUS;
//            }
        if (team.equals("White")) {
            return whiteScore - blackScore;
//            return whiteScore;
        } else {
            return blackScore - whiteScore;
//            return blackScore;
        }
    }
}