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

        System.out.println("\n\nUniversal Set: " + universalSet);
        printList(sets);

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
        
        System.out.println("\n\nStarting to backtrack");

        // is_a_solution
        if(remainingNums.isEmpty())
        {
            //process_solution
            System.out.println("\n\nYou need a total of " + answerList.size() + " sets");
            printList(answerList);
            return true;
        }
        else{
            // For each element in remainingNums, if any of the sets in sets contains the element, add that set to answerList after removing it from remainingNums
            contructCandidates(remainingNums, sets);
            
            System.out.println("\n\nDone Constructing Candidates");

            answerList.add(possibleSets.get(0));

            System.out.println("\n\nPrinting Answer List : ");
            printList(answerList);

            remainingNums.removeAll(possibleSets.get(0));

            System.out.println("\n\nPrinting remaining Nums : " + remainingNums);

            sets.remove(possibleSets.get(0));

            System.out.println("\n\nPrinting remaining sets : ");
            printList(sets);

            if(backTrack(remainingNums, sets, answerList)) return true;

            System.out.println("\n\n\t\t\t\t\tBack Tracking : ");


            System.out.println("\n\nPrinting pre-answer List : ");
            printList(answerList);

            answerList.remove(possibleSets.get(0));

            System.out.println("\n\nPrinting post-answer List : ");
            printList(answerList);

            System.out.println("\n\nPrinting pre-remaining nums : " + remainingNums);

            remainingNums.addAll(possibleSets.get(0));

            System.out.println("\n\nPrinting pre-remaining nums : " + remainingNums);

            System.out.println("\n\nPrinting all pre-remaining sets : ");
            printList(sets);

            sets.add(possibleSets.get(0));

            System.out.println("\n\nPrinting all post-remaining sets : ");
            printList(sets);
        }
        return false;
    }

    private static void contructCandidates(HashSet<Integer> remainingNums, List<HashSet<Integer>> allSets)
    {
        System.out.println("Constructing candidates ... \n");

        HashSet<HashSet<Integer>> tempSet = new HashSet<HashSet<Integer>>();

        for(int i: remainingNums)
        {
            for(HashSet<Integer> set: allSets)
            {
                if(set.contains(i))
                {
                    tempSet.add(set);
                }
            }
        }

        possibleSets = new ArrayList<>(tempSet);

        System.out.println("Constructed " + possibleSets.size() + " candidates: ");

        printList(possibleSets);
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

    private static void printList(List<HashSet<Integer>> toPrint)
    {
        for (int i = 0; i < toPrint.size(); i++) {
            System.out.println(toPrint.get(i) + " ");
        }
    }
}
