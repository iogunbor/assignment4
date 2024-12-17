import java.util.Map;

public class LPHashTable {
    private int size;
    private Map.Entry<String, Integer>[] table;
    private HashFunction hashFunc;


    @SuppressWarnings("unchecked")
    public LPHashTable(int size, HashFunction hashFunc) {
        this.size = size;
        this.table = new Map.Entry[size];
        this.hashFunc = hashFunc;
    }

    public void insert(String key, int value) {
        int index = Math.abs(hashFunc.hash(key) % size);
        while (table[index] != null) {
            index = (index + 1) % size;
        }
        table[index] = Map.entry(key, value);
    }

    public int search(String key) {
        int index = Math.abs(hashFunc.hash(key) % size);
        int comparisons = 0;
        while (table[index] != null) {
            comparisons++;
            if (table[index].getKey().equals(key)) {
                return comparisons;
            }
            index = (index + 1) % size;
        }
        
        return comparisons;
    }
    
}
