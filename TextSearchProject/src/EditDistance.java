import java.util.*;

public class EditDistance {
    static int distance(String a, String b) {
        int n = a.length(), m = b.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
            }
        }
        return dp[n][m];
    }

    static void run(Scanner sc, DataSet data) {
        System.out.print("\nEnter word for spelling comparison: ");
        String input = sc.nextLine().toLowerCase().trim();
        ArrayList<String> words = data.getWords();
        HashSet<String> unique = new HashSet<>(words);
        String best = "";
        int bestDistance = Integer.MAX_VALUE;
        for (String word : unique) {
            if (word.length() == 0) continue;
            int d = distance(input, word);
            if (d < bestDistance) { bestDistance = d; best = word; }
        }
        System.out.println("\n========== EDIT DISTANCE ==========");
        System.out.println("Input word : " + input);
        System.out.println("Closest word: " + best);
        System.out.println("Edit distance: " + bestDistance);
    }
}
