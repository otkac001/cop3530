import cop3530.HashingFunction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// SeparateChaining Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// void makeEmpty( )      --> Remove all items

public class HashMap<KeyType,ValueType> implements Map<KeyType,ValueType>, 
{
    private HashingFunction<KeyType> hashFunc;
    private double loadFactorLimit;
    //weiss's vars
    private static final int DEFAULT_TABLE_SIZE = 101;
    /** The array of Lists. */
    private List<Map.Entry<KeyType,ValueType>> [] theLists; 
    private int currentSize;
    
    public HashMap( )
    {
    }
    
    /**
     * Construct the hash Map.
     * @param maxLoad approximate table size.
     */
    public HashMap( double maxLoad )
    { theLists = new LinkedList[ nextPrime( maxLoad ) ];
        for( int i = 0; i < theLists.length; i++ )
            theLists[ i ] = new LinkedList<>( ); }
    public HashMap( HashingFunction<KeyType> hf )
    { /* Implement */ }
    public HashMap( HashingFunction<KeyType> hf, double maxLoad )
    { /* Implement */ }
    
    /**
     * Returns set of all entries in the map.
     * @return set of all entries of HashMap.
     */
    public Set<Map.Entry<KeyType,ValueType>> entrySet()
     {
         Set<Map.Entry<KeyType,ValueType>> output = null ;
         for (List<Map.Entry<KeyType,ValueType>> index: theLists)
         {
             for (Map.Entry<KeyType,ValueType> row : index)
             {
                 output.add(row);
             }  
         }
         return output;
     }
    /**
     * Returns a Collection view of the values contained in this map.
     */
    public Collection<ValueType> values()
    {
        Collection<ValueType> output = new ArrayList<>();
        for (List<Map.Entry<KeyType,ValueType>> index: theLists)
         {
             for (Map.Entry<KeyType,ValueType> row : index)
             {
                 output.add(row.getValue());
             }  
         }
        return output;  
    }
    
    /**
     * Returns set of all keys of map.
     */
    public Set<KeyType> keySet()
    {
        Set<Map.Entry<KeyType,ValueType>> output = null ;
         for (List<Map.Entry<KeyType,ValueType>> index: theLists)
         {
             for (Map.Entry<KeyType,ValueType> row : index)
             {
                 output.add(row.getKey());
             }  
         }
         return output;
    }
    /**
     * Gets the value of specified key.
     * @param k KeyType
     * @return the value in the key
     */
    private ValueType get( KeyType k )
    {
        
        
    }
    
    public void clear( )
    {
        
    }
    /**
     * Searches for a key in the hashMap.
     * this is part pseudocode kip wrote in class
     */
    private int indexOf(List<Map.Entry<KeyType,ValueType>>[] list,KeyType key)
    {
        int i=0;
        boolean match = false;
        for ()//each entry in the List
        {
            if (myHash==null)
                match = (entry.key==key);
            else
                match= hashFunc.equals(Entry.key,key);
            if (match)
                return;
            else
            i++;
        }
        return -1;
     
    }
    /**
     * this is part pseudocode kip wrote in class
     */
    public ValueType put( KeyType key, ValueType value )
    {
        int hashCode = hashFunc.hashCode(key);
        List<Map.Entry<KeyType,ValueType>>[] list = the Lists[hashCode];
        int index = indexOf(list, key);
        if (index !=1 )
            list.get(index).set(value);
        else
        {
            list.add(new Entry(key, value))
                    if (list is full)
                    rehash();
         }
            
        
    }
    /**
     * Insert into the hash table. If the item is
     * already present, then do nothing.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        List<Map.Entry<KeyType,ValueType>> whichList = theLists[ myhash( x ) ];
        if( !whichList.contains( x ) )
        {
            whichList.add( x );

                // Rehash; see Section 5.5
            if( ++currentSize > theLists.length )
                rehash( );
        }
    }

    /**
     * Remove from the hash table.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        List<Map.Entry<KeyType,ValueType>> whichList = theLists[ myhash( x ) ];
        if( whichList.contains( x ) )
    {
        whichList.remove( x );
            currentSize--;
    }
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return true if x isnot found.
     */
    public boolean contains( AnyType x )
    {
        List<Map.Entry<KeyType,ValueType>> whichList = theLists[ myhash( x ) ];
        return whichList.contains( x );
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty( )
    {
        for( int i = 0; i < theLists.length; i++ )
            theLists[ i ].clear( );
        currentSize = 0;    
    }

    /**
     * A hash routine for String objects.
     * @param key the String to hash.
     * @param tableSize the size of the hash table.
     * @return the hash value.
     */
    public static int hash( String key, int tableSize )
    {
        int hashVal = 0;

        for( int i = 0; i < key.length( ); i++ )
            hashVal = 37 * hashVal + key.charAt( i );

        hashVal %= tableSize;
        if( hashVal < 0 )
            hashVal += tableSize;

        return hashVal;
    }

    /**
     * Enlarge the map.
     */
    private void rehash( )
    {
        List<Map.Entry<KeyType,ValueType>> [ ]  oldLists = theLists;

            // Create new double-sized, empty table
        theLists = new List[ (int)(nextPrime( 2.0 * theLists.length )) ];
        for( int j = 0; j < theLists.length; j++ )
            theLists[ j ] = new LinkedList<>( );

            // Copy table over
        currentSize = 0;
        for( int j = 0; j < oldLists.length; j++ )
            for( List<Map.Entry<KeyType,ValueType>> list : oldLists )
                theLists[ j ] = list;
    }

    private int myhash( AnyType x )
    {
        int hashVal = x.hashCode( );

        hashVal %= theLists.length;
        if( hashVal < 0 )
            hashVal += theLists.length;

        return hashVal;
    }
    
    

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static double nextPrime( double n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime( double n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2.0 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }

}
