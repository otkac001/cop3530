package cop3530;

import java.util.HashMap;
import java.util.Map;

public class Assignment4 {

    public static int getMaxDistance(String word, String[] edges){
        Map<String,Integer> charIndexes = new HashMap();
        for(int i = 0; i<word.length(); i++)
            charIndexes.put(word.substring(i, i+1), i);
        int max = 0;
        for(String e : edges){
            int vertexIndex1 = charIndexes.get(e.substring(0, 1));
            int vertexIndex2 = charIndexes.get(e.substring(1));
            int distance = Math.abs(vertexIndex1 - vertexIndex2) + 1;
            if(distance>max) max = distance;
        }
        return max;
    }

    public static void main(String args[]){
        
    }

}

private ArrayList<String> letters = new ArrayList<>();

    public ArrayList<String> permutationGenerator(String Letters) 
    {
        
        permutation("", Letters);
        return letters;
    }

    
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
