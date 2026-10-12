import java.util.*;

public class AhoCorasick {
    static class Node {
        Node[] child = new Node[26];
        Node fail;
        ArrayList<String> output = new ArrayList<>();
    }
    static Node root;

    static void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c < 'a' || c > 'z') continue;
            int idx = c - 'a';
            if (cur.child[idx] == null) cur.child[idx] = new Node();
            cur = cur.child[idx];
        }
        cur.output.add(word);
    }

    static void buildFailureLinks() {
        Queue<Node> q = new LinkedList<>();
        root.fail = root;
        for (int i = 0; i < 26; i++) {
            if (root.child[i] != null) { root.child[i].fail = root; q.add(root.child[i]); }
            else root.child[i] = root;
        }
        while (!q.isEmpty()) {
            Node cur = q.remove();
            for (int i = 0; i < 26; i++) {
                if (cur.child[i] != null) {
                    Node next = cur.child[i];
                    next.fail = cur.fail.child[i];
                    for (String word : next.fail.output) if (!next.output.contains(word)) next.output.add(word);
                    q.add(next);
                } else cur.child[i] = cur.fail.child[i];
            }
        }
    }

    static void run(Scanner sc, DataSet data) {
        System.out.print("\nHow many keywords? ");
        int n = Integer.parseInt(sc.nextLine().trim());
        root = new Node();
        ArrayList<String> keywords = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Keyword " + (i + 1) + ": ");
            String word = sc.nextLine().toLowerCase().trim().replaceAll("[^a-z]", "");
            if (!word.isEmpty()) { keywords.add(word); insert(word); }
        }
        buildFailureLinks();
        HashMap<String, Integer> counts = new HashMap<>();
        for (String k : keywords) counts.put(k, 0);
        String text = data.getText();
        // Run the automaton once over the complete selected dataset.
        Node cur = root;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'a' || c > 'z') { cur = root; continue; }
            cur = cur.child[c - 'a'];
            for (String word : cur.output) counts.put(word, counts.get(word) + 1);
        }
        System.out.println("\n========== AHO-CORASICK ==========");
        for (String k : keywords) System.out.println(k + " -> " + counts.get(k) + " occurrence(s)");
        System.out.println("Total matches: " + counts.values().stream().mapToInt(Integer::intValue).sum());
    }

}
