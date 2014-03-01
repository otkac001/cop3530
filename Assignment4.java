/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cop3530;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Miranda
 */
public class Assignment4 {

    public static int getMaxDistance(Map<String,Integer> word, List<String[]> edges){
        int max = 0;
        for(String[] e : edges){
            int distance = Math.abs(word.get(e[0]) - word.get(e[1])) + 1;
            if(distance > max) max = distance;
        }
        return max;
    }


    public ArrayList<String> permutationGenerator(String letters)
    {
        List<String> permutations = new LinkedList();
        return permutation("", letters, permutations);
    }


    private void permutation(String prefix, String str, List<String> permutations)
    {
        int n = str.length();
        if (n == 0)
            permutations.add(prefix);
        
        else
        {
            for (int i = 0; i < n; i++)
           permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

    public static void main(String args[]) throws FileNotFoundException{
        Scanner input = new Scanner(new File(""));
        int numCases = input.nextInt();
        for(int i=0; i<numCases; i++){

            int numEdges = input.nextInt();
            int charIndex = 0;
            Map<String, Integer> word = new HashMap();
            List<String[]> edges = new LinkedList();

            for(int e=0; e<numEdges; e++){
                String[] edge = input.nextLine().split(" ");
                for(String vertex : edge){
                    edges.add(edge);
                    if(!word.containsKey(vertex)){
                        word.put(vertex, charIndex++);
                    }
                }
            }

            List<String> permutations;
            int minMaxDistance = word.size() - 2;
            String bestWord = "";
            for(String p : permutations){
                int maxDistance = getMaxDistance(word, edges);
                if(maxDistance < minMaxDistance){
                    minMaxDistance = maxDistance;
                    bestWord = p;
                }
                if(maxDistance == 0) break;
            }
            System.out.println(bestWord + " = " + minMaxDistance);
        }
    }

}
