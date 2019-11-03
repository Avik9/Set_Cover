import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CombinatorialSearch {

    public static List<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>();
    public static List<HashSet<Integer>> answers = new ArrayList<HashSet<Integer>>();
    public static List<HashSet<Integer>> possibleSets = new ArrayList<HashSet<Integer>>();
    public static HashSet<Integer> universalSet;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // For program timing

        readFile(new File("All Files/s-X-12-6.txt"));

        System.out.println("Universal Set: " + universalSet);
        for (int i = 0; i < sets.size(); i++) {
            System.out.println(sets.get(i) + " ");
        }

        backTrack(universalSet, sets, answers);

        long endTime = System.currentTimeMillis(); // For program timing

        System.out.println("Total execution time: " + ((endTime - startTime) / 1000.0) + " s");

        
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

    private static boolean backTrack(HashSet<Integer> remainingNums, List<HashSet<Integer>> sets, List<HashSet<Integer>> answerList){
        
        System.out.println("Starting to backtrack");

        // is_a_solution
        if(remainingNums.isEmpty())
        {
            //process_solution
            System.out.println("You need a total of " + answerList.size() + " sets");
            for (int i = 0; i < answerList.size(); i++) {
                System.out.println(answerList.get(i) + " ");
            }
            return true;
        }
        else{
            // For each element in remainingNums, if any of the sets in sets contains the element, add that set to answerList after removing it from remainingNums
            contructCandidates(remainingNums, sets, possibleSets);
            answerList.add(possibleSets.get(0));
            remainingNums.removeAll(possibleSets.get(0));
            sets.remove(possibleSets.get(0));
            if(backTrack(remainingNums, sets, answerList)) return true;
            answerList.remove(possibleSets.get(0));
            remainingNums.addAll(possibleSets.get(0));
            sets.add(possibleSets.get(0));
        }
        return false;
    }

    /**
     *  This routine fills a list possibleSets with the complete set of possible
     *  candidates for the kth position of a, given the contents of the
     *  first k − 1 positions.
     *  The number of candidates returned in this array is denoted by
     *  nc.
     */
    private static void contructCandidates(HashSet<Integer> remainingNums, List<HashSet<Integer>> allSets, List<HashSet<Integer>> candidates)
    {
        System.out.println("Constructing candidates ... \n");

        for(int i: remainingNums)
        {
            for(HashSet<Integer> set: allSets)
            {
                if(set.contains(i))
                {
                    candidates.add(set);
                }
            }
        }

        System.out.println("Constructed candidates: ");

        for (int i = 0; i < candidates.size(); i++) {
            System.out.println(candidates.get(i) + " ");
        }
    }

    // private static void removeAll(HashSet<Integer> removeFrom, HashSet<Integer> sets)
    // {
    //     removeFrom.removeIf(entry -> {
    //         if(sets.contains(entry))
    //         {
    //             return true;
    //         }
    //         return false;
    //     });
    // }
}
