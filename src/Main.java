import BoardGame.Board;
import ChessPieces.Bishop;
import ChessPieces.King;
import ChessPieces.Piece;
import ChessPieces.Position;
import GameMechanics.GamePlayer;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static final String movePawn(String team) {
        Board board = Board.getInstance();
        if (board.getKing(team).isCheckMate()) {
            return "resign\n";
        }
        String output = "move ";
        ArrayList<Piece> pawns = board.getPawns(team);
        for (Piece pawn : pawns) {
            ArrayList<Position> moves = pawn.getMoves();
            if (moves.size() > 0) {
                output += pawn.toStringPosition() + moves.get(0);
                board.movePiece(pawn, moves.get(0));
                break;
            }
        }
        output += "\n";
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter((new OutputStreamWriter(System.out)));

        Board board = Board.getInstance();
        String team = new String();
        String nowPlaying = new String();
        GamePlayer gamePlayer = new GamePlayer(board);
        boolean forceMode = false;
        while (true) {
            String command = new String();
            command = reader.readLine();
            if (command.equals("xboard")) {
                board = board.getInstance();
                continue;
            }

            if (command.equals("new")) {
                board.init();
                team = "Black";
                forceMode = false;
                nowPlaying = "White";
                continue;
            }

            if (command.equals("force")) {
                forceMode = true;
                continue;
            }

            if (command.equals("go")) {
                forceMode = false;
                team = nowPlaying;
                writer.write(movePawn(team));
                writer.flush();
                if (nowPlaying.equals("White")) {
                    nowPlaying = "Black";
                } else {
                    nowPlaying = "White";
                }
                continue;
            }

            if (command.equals("White")) {
                team = "White";
                nowPlaying = "White";
                continue;
            }

            if (command.equals("Black")) {
                team = "Black";
                nowPlaying = "Black";
                continue;
            }

            if (command.equals("protover 2")) {
                String output = "feature sigint=0 san=0 done=1\n";
                writer.write(output);
                writer.flush();
                continue;
            }

            if (command.equals("quit")) {
                System.exit(0);
            }

            if (command.length() >= 4) {
                // Checks if it is a move from the xboard.
                boolean first = command.charAt(0) >= 'a' && command.charAt(0) <= 'h';
                boolean second = command.charAt(1) >= '1' && command.charAt(1) <= '8';
                boolean third = command.charAt(2) >= 'a' && command.charAt(2) <= 'h';
                boolean forth = command.charAt(3) >= '1' && command.charAt(3) <= '8';
                if (first && second && third && forth) {
                    if (forceMode == true) {
                        if (nowPlaying.equals("Black"))
                            nowPlaying = "White";
                        else
                            nowPlaying = "Black";
                        board.moveEnemyPiece(command);
                        continue;
                    }
                    board.moveEnemyPiece(command);
                    writer.write(gamePlayer.playTurn(team));  // make next move
                    writer.flush();
                    continue;
                }
            }
        }
    }
}