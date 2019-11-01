import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CombinatorialSearch {

    public static List<List<Integer>> sets = new ArrayList<List<Integer>>();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();// For program timing
        readFile(new File("All Files/s-X-12-6.txt"));
        long endTime = System.currentTimeMillis(); // For program timing
        System.out.println("Total execution time: " + ((endTime - startTime) / 1000.0) + " s");

        // for (int i = 0; i < sets.size(); i++) {
        //     for (int j = 0; j < sets.get(i).size(); j++) {
        //         System.out.print(sets.get(i).get(j) + " ");
        //     }
        //     System.out.println();
        // }
    }

    // Reads in the file then populates the sets
    private static void readFile(File f) {
        try {
            // Reads in the different elements in each set
            Scanner sc = new Scanner(f);
            int setSize = sc.nextInt();
            int numberOfSubsets = sc.nextInt();

            List<Integer> lst = new ArrayList();
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
                sets.add(new ArrayList(lst));
                lst.clear();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}