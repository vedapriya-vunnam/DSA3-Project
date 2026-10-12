import java.util.*;

public class SuffixArray {
    static int[] buildSuffixArray(String text) {
        int n = text.length();
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) index[i] = i;
        Arrays.sort(index, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) { return text.substring(a).compareTo(text.substring(b)); }
        });
        int[] result = new int[n];
        for (int i = 0; i < n; i++) result[i] = index[i];
        return result;
    }

    static void run(Scanner sc, DataSet data) {
        String text = data.getText();
        int limit = Math.min(6000, text.length());
        text = text.substring(0, limit);
        System.out.println("\nBuilding suffix array using first " + limit + " characters...");
        int[] sa = buildSuffixArray(text);
        System.out.println("\n========== SUFFIX ARRAY ==========");
        System.out.println("Characters indexed: " + limit);
        System.out.println("First 20 suffix indexes:");
        for (int i = 0; i < Math.min(20, sa.length); i++) System.out.println(i + " -> " + sa[i]);
        System.out.println("Total suffixes: " + sa.length);
    }
}
