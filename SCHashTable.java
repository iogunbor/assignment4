import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SCHashTable {
    private int size;
    private List<Map.Entry<String, Integer>>[] table;
    private HashFunction hashFunc;


    @SuppressWarnings("unchecked")
    public SCHashTable(int size, HashFunction hashFunc) {
        this.size = size;
        this.table = new List[size];
        this.hashFunc = hashFunc;
    }

    public void insert(String key, int value) {
        int index = Math.abs(hashFunc.hash(key) % size);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        table[index].add(Map.entry(key, value));
    }

    public int search(String key) {
        int index = Math.abs(hashFunc.hash(key) % size);
        int comparisons = 0;
        if (table[index] != null) {
            for (Map.Entry<String, Integer> entry : table[index]) {
                comparisons++;
                if (entry.getKey().equals(key)) {
                    return comparisons;
                }
            }
        }
        
        return comparisons;
    }
    
}
