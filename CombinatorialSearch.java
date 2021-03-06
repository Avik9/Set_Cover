import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class CombinatorialSearch {

    public static List<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>();
    public static List<HashSet<Integer>> singleSets = new ArrayList<HashSet<Integer>>();
    public static List<HashSet<Integer>> answers = new ArrayList<HashSet<Integer>>();
    public static List<List<HashSet<Integer>>> possibleSets = new ArrayList<List<HashSet<Integer>>>();
    public static HashSet<Integer> universalSet = new HashSet<Integer>();

    public static void main(String[] args) {
        readFile(new File("All Files/s-X-12-6.txt"));
        if (backTrack(sets, answers))
            printList(answers);
        else {
            sets.addAll(singleSets);
            backTrack(sets, answers);
            printList(answers);
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
            for (int i = 1; i <= setSize; i++)
                universalSet.add(i);
            Set<Integer> lst = new HashSet<Integer>();
            String line = sc.nextLine();
            List<String> stringList = new ArrayList<String>();
            int num;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.equals("")) {
                    sets.add(new HashSet<Integer>());
                    lst.clear();
                    continue;
                }
                stringList = Arrays.asList(line.split(" "));
                for (int j = 0; j < stringList.size(); j++) {
                    num = Integer.parseInt(stringList.get(j));
                    lst.add(num);
                }
                if (lst.size() == 1)
                    singleSets.add(new HashSet<Integer>(lst));
                else {
                    sets.add(new HashSet<Integer>(lst));
                    lst.clear();
                }
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static boolean backTrack(List<HashSet<Integer>> sets, List<HashSet<Integer>> answerList) {
        // construct_candidates()
        contructCandidates(sets);

        if(possibleSets.isEmpty())
        {
            return false;
        }

        HashSet<Integer> tempSet = new HashSet<Integer>();
        for (HashSet<Integer> s : possibleSets.get(0))
            tempSet.addAll(s);

        // is_a_solution()
        if (tempSet.size() == universalSet.size()) {
            // process_solution()
            answers = possibleSets.get(0);
            return true;
        }
        return false;
    }

    private static void contructCandidates(List<HashSet<Integer>> allSets) {
        for (long i = 0; i < (double)(1 << allSets.size()); i++) {
            List<HashSet<Integer>> combination = new ArrayList<HashSet<Integer>>();
            for (int j = 0; j < allSets.size(); j++) {
                if (((i / (int) Math.pow(2, j)) & 1) != 0)
                    combination.add(allSets.get(j));
            }

            HashSet<Integer> tempSet = new HashSet<Integer>();
            for (HashSet<Integer> s : combination)
                tempSet.addAll(s);

            if (tempSet.size() == universalSet.size())
                possibleSets.add(combination);
        }
        possibleSets = possibleSets.stream().sorted((o1, o2) -> {
            return Integer.compare(o1.size(), o2.size());
        }).collect(Collectors.toList());
    }

    private static void printList(List<HashSet<Integer>> toPrint) {
        System.out.println("At least " + toPrint.size() + " sets are required. The subsets required are: ");

        for (int i = 0; i < toPrint.size(); i++)
            System.out.println(toPrint.get(i) + " ");
    }
}