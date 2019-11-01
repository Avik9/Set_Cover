import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CombinatorialSearch {

    public static List<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>();
    public static HashSet<Integer> universalSet;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();// For program timing
        readFile(new File("All Files/s-X-12-6.txt"));
        long endTime = System.currentTimeMillis(); // For program timing
        System.out.println("Total execution time: " + ((endTime - startTime) / 1000.0) + " s");

        System.out.println("Universal Set: " + universalSet);
        for (int i = 0; i < sets.size(); i++) {
            System.out.println(sets.get(i) + " ");
        }
    }

    // Reads in the file then populates the sets
    private static void readFile(File f) {
        try {
            // Reads in the different elements in each set
            Scanner sc = new Scanner(f);
            int setSize = sc.nextInt();
            int numberOfSubsets = sc.nextInt();

            universalSet = new HashSet<>(setSize);
            for(int i = 1; i <= setSize; i++) universalSet.add(i);
            Set<Integer> lst = new HashSet();
            String line = sc.nextLine();
            List<String> stringList = new ArrayList<String>();
            int num;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                stringList = Arrays.asList(line.split(" "));
                for (int j = 0; j < stringList.size(); j++) {
                    num = Integer.parseInt(stringList.get(j));
                    lst.add(num);
                }
                sets.add(new HashSet<>(lst));
                lst.clear();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}