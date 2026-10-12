import java.io.*;
import java.util.*;

public class DataSet {
    private String filePath;
    private String text;

    public DataSet(String filePath) throws IOException {
        this.filePath = filePath;
        this.text = readAll(filePath).toLowerCase();
    }

    private static String readAll(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append(' ');
        }
        br.close();
        return sb.toString();
    }

    public String getFilePath() { return filePath; }
    public String getText() { return text; }

    public int getLineCount() {
        int count = 0;
        for (int i = 0; i < text.length(); i++) if (text.charAt(i) == '\n') count++;
        return Math.max(1, count);
    }

    public int getWordCount() {
        StringTokenizer st = new StringTokenizer(text, " \t\r\n,.;:!?()[]{}\"'/-");
        int count = 0;
        while (st.hasMoreTokens()) { st.nextToken(); count++; }
        return count;
    }

    public ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(text, " \t\r\n,.;:!?()[]{}\"'/-");
        while (st.hasMoreTokens()) {
            String word = st.nextToken().replaceAll("[^a-z]", "");
            if (!word.isEmpty()) words.add(word);
        }
        return words;
    }

    public void showInfo() {
        File f = new File(filePath);
        System.out.println("\n========== DATASET INFORMATION ==========");
        System.out.println("Source     : " + filePath);
        System.out.println("File Size  : " + f.length() + " bytes");
        System.out.println("Characters : " + text.length());
        System.out.println("Words      : " + getWordCount());
        System.out.println("=========================================");
    }
}
