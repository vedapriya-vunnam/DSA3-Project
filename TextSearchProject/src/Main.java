import java.io.*;
import java.util.*;

public class Main {
    static DataSet data;

    static void loadDataSet(Scanner sc) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("       SELECT DATASET SOURCE");
            System.out.println("========================================");
            System.out.println("1. Use Predefined Large Dataset");
            System.out.println("2. Use My Own Dataset");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();
            try {
                if (choice.equals("1")) {
                    data = new DataSet("datasets/predefined/search_data.txt");
                    System.out.println("\nPredefined large dataset loaded successfully.");
                    data.showInfo();
                    operations(sc);
                } else if (choice.equals("2")) {
                    System.out.print("\nEnter path of your .txt dataset: ");
                    String path = sc.nextLine().trim();
                    data = new DataSet(path);
                    System.out.println("\nUser dataset loaded successfully.");
                    data.showInfo();
                    operations(sc);
                } else if (choice.equals("3")) {
                    System.out.println("Thank you!");
                    return;
                } else System.out.println("Invalid choice.");
            } catch (IOException e) {
                System.out.println("Could not read dataset: " + e.getMessage());
            }
        }
    }

    static void operations(Scanner sc) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("          SELECT OPERATION");
            System.out.println("========================================");
            System.out.println("1. Find a particular word/pattern (KMP)");
            System.out.println("2. Find spelling matches (Edit Distance)");
            System.out.println("3. Search multiple keywords (Aho-Corasick)");
            System.out.println("4. Build text index (Suffix Array)");
            System.out.println("5. Analyze common prefixes (LCP Array)");
            System.out.println("6. Show dataset information");
            System.out.println("7. Select another dataset");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": KMP.run(sc, data); break;
                case "2": EditDistance.run(sc, data); break;
                case "3": AhoCorasick.run(sc, data); break;
                case "4": SuffixArray.run(sc, data); break;
                case "5": LCPArray.run(sc, data); break;
                case "6": data.showInfo(); break;
                case "7": return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        loadDataSet(new Scanner(System.in));
    }
}
