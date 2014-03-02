package cop3530;
/**
 * By: Olena Tkachenko and Miguel Chateloin
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;

public class Assignment4
{
    private ArrayList<String> letters = new ArrayList<>();

    //calculates max distance between friends for 1 word
     public static int getMaxDistance(List<String[]> edges, String word)
     {
        int max = 0;
        for(String[] e : edges)
        {
            int distance = Math.abs(word.indexOf(e[0]) - word.indexOf(e[1]));
            if(distance > max) max = distance;
        }
        return max;
    }
     //calculates lowest max distance between friends for all the permutations
    public String[] lowestMaxDistance( List<String[]> edges, String origLetters)
    {
        String[] out = new String[2];
        letters = permutationGenerator(origLetters);
        //sorts all the permutaions of letters
        Collections.sort(letters);
        int minMaxDistance = origLetters.length()-1;
        for(String e: letters)
        {
           int maxDistance = getMaxDistance(edges, e);
                if(maxDistance < minMaxDistance){
                    minMaxDistance = maxDistance;
                    out[0] = e;
                    out[1] = "" + minMaxDistance;
                }
                else if(maxDistance == 0)
                {
                    out[0] = e;
                    out[1] = "" + maxDistance;
                    return out;
                }
                else
                {}
            
        }
        return out;
        
    }
  
    public ArrayList<String> permutationGenerator(String Letters) 
    {
        
        permutation("", Letters);
        return letters;
    }


   //helper method that produces all permutations of String str 
    private void permutation(String prefix, String str) 
    {
        int n = str.length();
        if (n == 0) 
            letters.add(prefix);
        else 
        {
            for (int i = 0; i < n; i++)
           permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }


    public static void main(String args[]) throws FileNotFoundException{
        Scanner input = new Scanner(new File("data.txt"));
        int numCases = input.nextInt();
        int numEdges = input.nextInt();
        System.out.println(input.nextLine());
        for(int i=0; i<numCases; i++)
        {
            int charIndex = 0;
            Map<String, Integer> word = new HashMap();
            List<String[]> edges = new LinkedList();
            //reinitialize numEdges becouse nextInt is not the same as nextLine()
            if (i>0)
            {
                numEdges = input.nextInt();
                System.out.println(input.nextLine());
            }
            for(int e=0; e<numEdges; e++)
            {
                    String line = input.nextLine();
                    String[] edge = line.split(" ");
                    edges.add(edge);
                    for(String vertex : edge){
                    if(!word.containsKey(vertex)){
                        word.put(vertex, charIndex);
                        charIndex++;
                    }
                }
                
            }
            Collection<String> letters = word.keySet();//list of unduplicated letters input
            String unduplicated = "";
            for (String e: letters)
            {
                unduplicated += e;
            }
            
            Assignment4 friends = new Assignment4();
            String[] out = friends.lowestMaxDistance(edges, unduplicated);
            // put spaces between output characters
            String bestWord = "";
            for (int x=0; x<out[0].length(); x++)
            {
                bestWord += out[0].substring(x,x+1)+" ";
            }
            //printing the message    
            String minMaxDistance = out[1];
            System.out.println(bestWord + " = " + minMaxDistance);
        }
    }


}
