package Helper;

// Helper class contains functions that might be used in multiple places
// but aren't specific to any class
public class Helper {
    public static String enemyTeam(String team) {
        if (team.equals("White")) {
            return "Black";
        } else {
            return "White";
        }
    }
}
