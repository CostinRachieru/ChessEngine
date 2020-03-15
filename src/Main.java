import BoardGame.Board;
import ChessPieces.Piece;
import ChessPieces.Position;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter((new OutputStreamWriter(System.out)));

        Board board = Board.getInstance();
        String team = new String();
        while (true) {
            String command = new String();
            command = reader.readLine();
            Integer len = command.length();
            if (command.equals("xboard")) {
                continue;
            }

            if (command.equals("new")) {
                board = Board.getInstance();
                team = "Black";
                continue;
            }

            if (command.equals("protover 2")) {
                String output = "feature sigint=0 done=1\n";
                writer.write(output);
                writer.flush();
                continue;
            }

            if (command.equals("quit")) {
                System.exit(0);
            }

            boolean first = command.charAt(0) >= 'a' && command.charAt(0) <= 'h';
            boolean second = command.charAt(1) >= '1' && command.charAt(1) <= '8';
            boolean third = command.charAt(2) >= 'a' && command.charAt(2) <= 'h';
            boolean forth = command.charAt(3) >= '1' && command.charAt(3) <= '8';
            if (first && second && third && forth) {
                board.moveEnemyPiece(command);
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
                writer.write(output);
                writer.flush();
                continue;
            }
        }
    }
}
