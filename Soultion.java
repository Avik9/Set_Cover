import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class Soultion{

    public static List<HashSet<Integer>> tempSets = new ArrayList<HashSet<Integer>>();
    public static List<HashSet<Integer>> answers = new ArrayList<HashSet<Integer>>();
    public static HashSet<Integer> experimentalSet = new HashSet<Integer>();

    public static void main(String[] args) {
        readFile(new File("All Files/Test2.txt"));
        System.out.println("Printing All Lists");
        printList(tempSets);

        System.out.println("\n\nPrinting pruned Lists");
        prune();
        printList(answers);
    }

    // Reads in the file then populates the sets
    private static void readFile(File f) {
        try {
            // Reads in the different elements in each set
            Scanner sc = new Scanner(f);
            int setSize = sc.nextInt();
            int numberOfSubsets = sc.nextInt();

            Set<Integer> lst = new HashSet<Integer>();
            String line = sc.nextLine();
            List<String> stringList = new ArrayList<String>();
            int num;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.equals("")) {
                    tempSets.add(new HashSet<Integer>());
                    lst.clear();
                    continue;
                }
                stringList = Arrays.asList(line.split(" "));
                for (int j = 0; j < stringList.size(); j++) {
                    num = Integer.parseInt(stringList.get(j));
                    lst.add(num);
                }
                tempSets.add(new HashSet<Integer>(lst));
                lst.clear();
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void printList(List<HashSet<Integer>> toPrint) {
        for (int i = 0; i < toPrint.size(); i++)
            System.out.println((i+1) + ") " + toPrint.get(i) + " ");
    }

    private static void prune()
    {
        tempSets = tempSets.stream().sorted((o1, o2) -> {
            return Integer.compare(o2.size(), o1.size());
        }).collect(Collectors.toList());

        for(HashSet<Integer> s: tempSets)
        {
            if(!experimentalSet.containsAll(s))
            {
                // System.out.println("Old experimental set " + experimentalSet);
                // System.out.println("Adding " + s);
                experimentalSet.addAll(s);
                // System.out.println("New experimental set " + experimentalSet);

                answers.add(s);
            }
        }
    }
}
