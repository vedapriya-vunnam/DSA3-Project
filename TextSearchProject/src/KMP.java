import java.util.*;

public class KMP {
    static int[] buildLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0, i = 1;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i++] = ++len;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }

    static ArrayList<Integer> search(String text, String pattern) {
        ArrayList<Integer> positions = new ArrayList<>();
        if (pattern.length() == 0) return positions;
        int[] lps = buildLPS(pattern);
        int i = 0, j = 0;
        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) { i++; j++; }
            if (j == pattern.length()) {
                positions.add(i - j);
                j = lps[j - 1];
            } else if (i < text.length() && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) j = lps[j - 1]; else i++;
            }
        }
        return positions;
    }

    static void run(Scanner sc, DataSet data) {
        System.out.print("\nEnter word/pattern to search: ");
        String pattern = sc.nextLine().toLowerCase().trim();
        ArrayList<Integer> positions = search(data.getText(), pattern);
        System.out.println("\n========== KMP SEARCH ==========");
        System.out.println("Pattern: " + pattern);
        System.out.println("Total occurrences: " + positions.size());
        if (positions.isEmpty()) System.out.println("Pattern not found.");
        else {
            System.out.println("First " + Math.min(20, positions.size()) + " positions:");
            for (int i = 0; i < Math.min(20, positions.size()); i++)
                System.out.println(positions.get(i));
            if (positions.size() > 20) System.out.println("... remaining positions omitted");
        }
    }
}
