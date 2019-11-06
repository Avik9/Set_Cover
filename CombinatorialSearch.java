import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CombinatorialSearch {

    public static List<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>();
    public static List<HashSet<Integer>> answers = new ArrayList<HashSet<Integer>>();
    public static List<List<HashSet<Integer>>> possibleSets = new ArrayList<List<HashSet<Integer>>>();
    public static HashSet<Integer> universalSet;

    public static void main(String[] args) {
        // long startTime = System.currentTimeMillis(); // For program timing

        readFile(new File("All Files/s-rg-63-25.txt"));

        backTrack(sets, answers);
        printList(answers);

        // long endTime = System.currentTimeMillis(); // For program timing

        // System.out.println("Total execution time: " + ((endTime - startTime) / 1000.0) + " s");
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
            Set<Integer> lst = new HashSet();
            String line = sc.nextLine();
            List<String> stringList = new ArrayList<String>();
            int num;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if(line.equals(""))
                {
                    sets.add(new HashSet<>());
                    lst.clear();
                    continue;
                }
                stringList = Arrays.asList(line.split(" "));
                for (int j = 0; j < stringList.size(); j++) {
                    num = Integer.parseInt(stringList.get(j));
                    lst.add(num);
                }
                sets.add(new HashSet<>(lst));
                lst.clear();
            }
            Collections.sort(sets, new Comparator<Set<?>>() {
                @Override
                public int compare(Set<?> o1, Set<?> o2) {
                    return Integer.valueOf(o2.size()).compareTo(o1.size());
                }
            });
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static boolean backTrack(List<HashSet<Integer>> sets, List<HashSet<Integer>> answerList)
    {
        // construct_candidates()
        contructCandidates(sets);
    
        for(List<HashSet<Integer>> l: possibleSets)
        {
            HashSet<Integer> tempSet = new HashSet<Integer>();
            for(HashSet<Integer> s: l)
            {
                tempSet.addAll(s);
            }

            // is_a_solution()
            if(tempSet.size() == universalSet.size())
            {
                // process_solution()
                answers = l;
                return true;
            }
        }
        return false;
    }

    private static void contructCandidates(List<HashSet<Integer>> allSets)
    {
        for(int i = 0; i < (1 << allSets.size()); i++)
        {
            List<HashSet<Integer>> combination = new ArrayList<>();
            for(int j = 0; j < allSets.size(); j++) 
            {
                if (((i/(int)Math.pow(2, j)) & 1) != 0)
                {
                    combination.add(allSets.get(j));
                }
            }
            possibleSets.add(combination);
        }
        possibleSets = possibleSets.stream().sorted((o1,o2) -> {
               return Integer.compare(o1.size(), o2.size());
        }).collect(Collectors.toList());
    }

    private static void printList(List<HashSet<Integer>> toPrint)
    {
        System.out.println("Atleast " + toPrint.size() + " sets are required. The subsets required are: ");

        for (int i = 0; i < toPrint.size(); i++) {
            System.out.println(toPrint.get(i) + " ");
        }
    }
}