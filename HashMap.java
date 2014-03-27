package cop3530;

import cop3530.HashingFunction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class HashMap<KeyType, ValueType> implements Map<KeyType, ValueType> {


    private static final double DEFAULT_LOAD_FACTOR = 0.7;
    private static final int DEFAULT_TABLE_SIZE = 101;

    private int tableSize;
    private HashingFunction<KeyType> hashFunc;
    private double loadFactorLimit;
    private List<Map.Entry<KeyType, ValueType>>[] theLists;
    //The number of key-value mappings contained in this map.
    private int currentSize;
    
    /**
     * Construct the hash map.
     */
    public HashMap() {
        this(null, DEFAULT_LOAD_FACTOR);
    }


    /**
     * Construct the hash map.
     *
     * @param maxLoad
     */
    public HashMap(double maxLoad) {
        this(null, maxLoad);
    }


    /**
     * Construct the hash map.
     *
     * @param hf
     */
    public HashMap(HashingFunction<KeyType> hf) {
        this(hf, DEFAULT_LOAD_FACTOR);
    }


    /**
     * Construct the hash map.
     *
     * @param hf
     * @param maxLoad
     */
    public HashMap(HashingFunction<KeyType> hf, double maxLoad) {
        loadFactorLimit = maxLoad;
        tableSize = DEFAULT_TABLE_SIZE;
        theLists = new LinkedList[tableSize];
        currentSize = 0;
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
    }
    
    /**
     * Returns an iterator of Map.Entry objects
     */
    public Iterator<Map.Entry<KeyType,ValueType>> iterator( )
    {
        return new MyIterator() ;
    }


    private class MyIterator implements Iterator<Map.Entry<KeyType,ValueType>>
    {
       Map.Entry<KeyType,ValueType> [] list = (Map.Entry<KeyType,ValueType> []) entrySet().toArray();
        private int index = 0;
        
        public boolean hasNext( )
        {
            return index < list.length;
        }
        
        
        public Map.Entry<KeyType,ValueType> next( )
        {
            if( !hasNext( ) ) 
                throw new java.util.NoSuchElementException( );   
            return list[index++];
        }
        
        public void remove( )
        {
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * Returns a String representation Entries in Hash Map.
     */
    public String toString( )
    {
        String sb = entrySet()+" ";
        return sb; 
    }
    
    /*
     * Return the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return currentSize;
    }


    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }


    @Override
    public boolean containsKey(Object key) {
        int hash = hashFunc != null ? hashFunc.hashCode((KeyType) key) : key.hashCode();
        List<Map.Entry<KeyType, ValueType>> row = theLists[hash];
        for (Map.Entry<KeyType, ValueType> item : row) {
            if (item.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns true if value is in the map.
     * @param value
     * @return true or false
     */
    @Override
    public boolean containsValue(Object value) {
        if (value == null){
            for(List<Map.Entry<KeyType, ValueType>> row : theLists)
                for(Map.Entry entry : row)
                    if(entry.getValue() == null)
                        return true;
        } else {
            for(List<Map.Entry<KeyType, ValueType>> row : theLists)
                for(Map.Entry entry : row)
                    if(entry.getValue().equals(value))
                        return true;     
        }
        return false;
    }


    @Override
    public void putAll(Map<? extends KeyType, ? extends ValueType> m) {
        for(Entry<? extends KeyType, ? extends ValueType> entry : m.entrySet()){
            put(entry.getKey(), entry.getValue());
        }
    }


    private class MyEntry implements Map.Entry<KeyType, ValueType> {


        private KeyType key;
        private ValueType value;


        public MyEntry(KeyType k, ValueType v) {
            key = k;
            value = v;
        }


        @Override
        public KeyType getKey() {
            return key;
        }


        @Override
        public ValueType getValue() {
            return value;
        }


        @Override
        public ValueType setValue(ValueType v) {
            value = v;
            return value;
        }
        
    /**
     * Returns a String representation Entry.
     */
    public String toString( )
    {
        String sb = "" +this;
        return sb ;
    }


    }


    /**
     * Returns set of all entries in the map.
     *
     * @return set of all entries of HashMap.
     */
    public Set<Map.Entry<KeyType, ValueType>> entrySet() 
    {
        Set<Map.Entry<KeyType, ValueType>> output = null;
        for (List<Map.Entry<KeyType, ValueType>> index : theLists) {
            for (Map.Entry<KeyType, ValueType> row : index) {
                output.add(row);
            }
        }
        return output;
    }


    /**
     *
     * @return Collection view of the values contained in map.
     */
    public Collection<ValueType> values() {
        Collection<ValueType> output = new ArrayList<>();
        for (List<Map.Entry<KeyType, ValueType>> index : theLists) {
            for (Map.Entry<KeyType, ValueType> row : index) {
                output.add(row.getValue());
            }
        }
        return output;
    }


    /**
     *
     * @return set of all keys of map.
     */
    public Set<KeyType> keySet() {
        Set<KeyType> output = new HashSet();
        for (List<Map.Entry<KeyType, ValueType>> row : theLists) {
            for (Map.Entry<KeyType, ValueType> entry : row) {
                output.add(entry.getKey());
            }
        }
        return output;
    }
    
    /**
     *
     * @return how many keys in theLists hash.
     */
    private int keySet(int hash) {
        Set<KeyType> output = new HashSet();
        List<Map.Entry<KeyType, ValueType>> list = theLists[hash];
        for (Map.Entry<KeyType, ValueType> entry : list) 
        {
            output.add(entry.getKey());
        }
        return output.size();
    }


    /**
     * Gets the value of specified key.
     *
     * @param key object
     * @param k KeyType
     * @return the value corresponding to the key k
     */
    @Override
    public ValueType get(Object key) 
    {
        int hash = hashFunc != null ? hashFunc.hashCode((KeyType) key) : myHash((KeyType)key);
        System.out.println(hash);
        List<Map.Entry<KeyType, ValueType>> row = theLists[hash];
        for (Map.Entry<KeyType, ValueType> item : row) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }


    /**
     * Put specified value with the specified key in this map.
     * @return the previous value associated with key,
     * or null if there was no mapping for key.
     */
    @Override
    public ValueType put(KeyType key, ValueType value)
    {
        int hash = hashFunc != null ? hashFunc.hashCode(key) : myHash(key);
        /**Testing:*/System.out.println("hash: "+hash);
        List<Map.Entry<KeyType, ValueType>> list = theLists[hash];
        
        
        int index = indexOf(theLists, key);
        if (index != -1) 
        {
            ValueType out = (ValueType)list.get(index);
            list.get(index).setValue(value);
            currentSize++;
            return out;
        }
        else
        {
            Map.Entry<KeyType, ValueType> entry = (Map.Entry<KeyType, ValueType>) new MyEntry(key, value);
            list.add(entry);
            currentSize++;
            if (list.size() == keySet(hash))
                rehash();
            return null;
        }


    }



    public void clear() {
        for (List<Map.Entry<KeyType, ValueType>> row : theLists) {
            row.clear();
        }
        currentSize = 0;
    }


    /**
     * Searches for a key in the hashMap.
     */
    private int indexOf(List<Map.Entry<KeyType, ValueType>>[] list, KeyType key) {
        int index = 0;
        Set<Map.Entry<KeyType, ValueType>> entrySet = entrySet();
        if (entrySet==null)
            return -1;
        for (Map.Entry<KeyType, ValueType> entry : entrySet())//each entry in the List
        {
            if (entry.getKey() == key || hashFunc.equals(entry.getKey(), key))
                return index;
            else
                index++;
        }
        return -1;

    }

    /*
     * Remove the mapping for the specified key from this map if present.
     * @return the previous value associated with key, or null if there was
     * no mapping for key.
     */
    @Override
    public ValueType remove(Object key) 
    {
        ValueType out = (ValueType) key;
        int hash = hashFunc != null ? hashFunc.hashCode((KeyType) key) : key.hashCode();
        List<Map.Entry<KeyType, ValueType>> whichList = theLists[hash];
        if (whichList.contains(key)) 
        {
            whichList.remove(key);
            currentSize--;
            return out ;
        }
        return null;
    }


    /**
     * A hash routine for String objects.
     *
     * @param key the String to hash.
     * @param tableSize the size of the hash table.
     * @return the hash value.
     */
    public int hashCode(String key) 
    {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal = 37 * hashVal + key.charAt(i);
        }


        hashVal %= tableSize;
        if (hashVal < 0) 
        {
            hashVal += tableSize;
        }

        return hashVal;
    }
    
    /**
     * A hash routine for String objects.
     *
     * @param key the String to hash.
     * @param tableSize the size of the hash table.
     * @return the hash value.
     */
    public int myHash(KeyType keyt)
    {
        String key = (String) keyt;
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal = 37 * hashVal + key.charAt(i);
        }


        hashVal %= tableSize;
        if (hashVal < 0) {
            hashVal += tableSize;
        }

        return hashVal;
    }


    /**
     * Enlarge the map.
     */
    private void rehash() {
        List<Map.Entry<KeyType, ValueType>>[] oldLists = theLists;

        tableSize = (int) (nextPrime(2.0 * theLists.length));
        // Create new double-sized prime, empty table
        theLists = new List[tableSize];
        for (int j = 0; j < theLists.length; j++) {
            theLists[ j] = new LinkedList<>();
        }


        // Copy table over
        for (int j = 0; j < oldLists.length; j++) {
            for (List<Map.Entry<KeyType, ValueType>> list : oldLists) {
                theLists[ j] = list;
            }
        }
    }


    /**
     * Internal method to find a prime number at least as large as n.
     *
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static double nextPrime(double n) {
        while (!isPrime(++n));
        return n;
    }


    /**
     * Internal method to test if a number is prime. Based on AKS primality
     * test: http://en.wikipedia.org/wiki/AKS_primality_test.
     *
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(double n) {
        //2 or 3 are prime. Multiples of 2 or 3 are not.
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }


        int i = 5, w = 2;
        while (i * i <= n) {
            if (n % i == 0) {
                return false;
            }
            i += w;
            w = 6 - w;
        }


        return true;
    }
}
