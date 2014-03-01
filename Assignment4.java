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


    public void input() throws FileNotFoundException 
    {
        int testCases = 0;                //number of test cases in data file
        String DistLetters = "";             //string of 1-8 distinct input letters
        ArrayList<String> testCaseLetters = new ArrayList<>();
        assg4 Friends = new assg4();       // assg object

        //Scanner object to read each line of file until eof
        Scanner infile = new Scanner(new File("data.txt"));

        //extract number of testcases in the file
        testCases = Integer.parseInt(infile.next());


        for (int i = 1; i<=testCases;i++ )     //do this number of test cases
        {
            
            String line = infile.next();

            //create a scanner obj associated with String line
            Scanner scanLine = new Scanner(line);
            // first token is  the integer specifying edges
            int edges = Integer.parseInt(scanLine.next());
            
            
            for (int j = 1; j<= edges; j++)    
            {
                //line = infile.nextLine();
                //System.out.println(line);
                //Scanner scanLine1 = new Scanner(line);
                String one = infile.next();
                String two = infile.next();
                //check for dooplicate letters
                DistLetters = (DistLetters.contains(one))? DistLetters:DistLetters+one ;
                DistLetters = (DistLetters.contains(two))? DistLetters:DistLetters+two ; 
            }
            testCaseLetters.add(DistLetters);
            DistLetters = "" ;
            scanLine.close();
            
        }
        for (String two: testCaseLetters)
        System.out.println(two);
        
        infile.close();         //close file
    }

    public static void main(String[] args) {
        assg4 Friends = new assg4();       // assg object
        try
        {
            Friends.input();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found.");  //if file not found
        }
        

    }   
