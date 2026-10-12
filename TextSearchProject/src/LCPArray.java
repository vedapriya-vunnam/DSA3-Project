import java.util.*;

public class LCPArray {
    static int[] buildSuffixArray(String text) {
        int n = text.length();
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) index[i] = i;
        Arrays.sort(index, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) { return text.substring(a).compareTo(text.substring(b)); }
        });
        int[] sa = new int[n];
        for (int i = 0; i < n; i++) sa[i] = index[i];
        return sa;
    }

    static int lcp(String a, String b) {
        int n = Math.min(a.length(), b.length()), i = 0;
        while (i < n && a.charAt(i) == b.charAt(i)) i++;
        return i;
    }

    static void run(Scanner sc, DataSet data) {
        String text = data.getText();
        int limit = Math.min(6000, text.length());
        text = text.substring(0, limit);
        int[] sa = buildSuffixArray(text);
        System.out.println("\n========== LCP ARRAY ==========");
        System.out.println("Characters analyzed: " + limit);
        System.out.println("First 20 LCP values:");
        int previous = 0;
        for (int i = 0; i < Math.min(20, sa.length); i++) {
            int value = (i == 0) ? 0 : lcp(text.substring(sa[i - 1]), text.substring(sa[i]));
            System.out.println(i + " -> suffix index " + sa[i] + ", LCP = " + value);
            previous = value;
        }
        System.out.println("Total LCP entries: " + sa.length);
    }
}
