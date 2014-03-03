package cop3530;
/**
 * By: Olena Tkachenko and Miguel Chateloin
 */


import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Assignment4 
{

    private Scanner input;
    private boolean runTimeReporting;
    public String output;                   

    public static void main(String args[]) throws FileNotFoundException 
    {
        Assignment4 assignment = new Assignment4();
        assignment.setRunTimeReporting(true);
        assignment.run();
        assignment.printOutput();
    }

    public Assignment4() throws FileNotFoundException {
        this(false);
    }


    public Assignment4( boolean reportRunTime) throws FileNotFoundException {
        this.input = new Scanner(System.in);
        this.runTimeReporting = reportRunTime;
    }


    public void setRunTimeReporting(boolean value) {
        this.runTimeReporting = value;
    }


    public void printOutput() {
        System.out.println(output);
    }


    public void run() 
    {
        int numCases = new Integer(input.nextLine());
        String caseOutput = "";
        for (int i = 0; i < numCases; i++) {
            long startTime = System.currentTimeMillis();


            ///Read in the edges and and collect the unique letters
            int numEdges = new Integer(input.nextLine());
            List<char[]> edges = new LinkedList();
            String letters = "";
            HashSet lettersUsed = new HashSet();
            for (int e = 0; e < numEdges; e++) {
                char[] edge = input.nextLine().replaceAll(" ", "").toCharArray();
                edges.add(edge);
                for (char vertex : edge) {
                    if (!lettersUsed.contains(vertex)) {
                        lettersUsed.add(vertex);
                        letters += vertex;
                    }
                }
            }
            
            String solution = lowestMaxDistance(edges, letters);


            double duration = (System.currentTimeMillis() - startTime);
            caseOutput += solution + (runTimeReporting ? ", avg. run time: " + duration + " ms" : "") + "\n";
        }


        this.output = caseOutput;
    }
    
    /**
     * Lexicographical permutation of a string. Implementation based on this algorithm:
     * http://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
     * @param letters
     * @return a list of all permutated strings
     */
    private List<String> permutate(String letters) {
        char[] characters = letters.toCharArray();
        Arrays.sort(characters);
        List<String> permutations = new LinkedList();
        permutations.add(new String(characters)); //The initial sorted word


        int k = -1;
        int l = -1;
        char temp;
        boolean kFound;
        
        do {   //Determine k index where k isn't last index in a word
            kFound = false;
            for (int i = characters.length - 2; i >= 0; i--) 
            {
                if (i != (characters.length - 1) && characters[i] < characters[i + 1]) {
                    k = i;
                    kFound = true;
                    break;
                }
            }

            if (kFound) {
                //Determine l index where a[l]>a[k]
                for (int i = characters.length - 1; i >= 0; i--) {
                    if (characters[k] < characters[i]) {
                        l = i;
                        break;
                    }
                }


                //Swap kth and lth value
                temp = characters[k];
                characters[k] = characters[l];
                characters[l] = temp;


                //Reverse sequence after k+1th index
                int sequenceLength = characters.length - k + 1;
                int sequenceMid = characters.length - (sequenceLength / 2) + 1;
                if (sequenceLength > 1) {
                    for (int i = k + 1, offset = 0; i < sequenceMid; i++, offset++) {
                        temp = characters[i];
                        characters[i] = characters[characters.length - 1 - offset];
                        characters[characters.length - 1 - offset] = temp;
                    }
                }


                permutations.add(new String(characters));
            }


        } while (kFound);


        return permutations;
    }
    

    /**
     * Calculates lowest max distance between friends for all the permutations
     * @param edges as a list of char[]'s
     * @param origLetters as a string word
     * @return the string found with the distance included
     */
    public String lowestMaxDistance( List<char[]> edges, String origLetters)
    {
        //Run through each permutation, getting the minimum of the maximum edge distances.
        int minMaxDistance = Integer.MAX_VALUE;
        String bestWord = "";
        for (String p : permutate(origLetters)) {
            int maxDistance = getMaxDistance(p, edges);
            if (maxDistance == 1) {
                break;          //We can't do better than this so stop here.
            }
            if (maxDistance < minMaxDistance) {
                minMaxDistance = maxDistance;
                bestWord = p;
            }
        }
        
        return bestWord.replaceAll("", " ").trim() + " = " + minMaxDistance; //Output has spaces between characters
    }
    
    
    /**
     * Calculates max distance between letters for 1 word
     * @param word as a string
     * @param edges list of char[]
     * @return maxDistance as an integer
     */
    private int getMaxDistance(String word, List<char[]> edges) {
        int charIndex = 0;
        int max = 1;
        for (char[] e : edges) {
            int distance = Math.abs(word.indexOf(e[0]) - word.indexOf(e[1]));
            if (distance > max) {
                max = distance;
            }
        }

        return max;
    }

}
