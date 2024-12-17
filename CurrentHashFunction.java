public class CurrentHashFunction implements HashFunction {
    @Override
    public int hash(String s) {
        int hash = 0;
        for (char c : s.toCharArray()) {
            hash = (hash * 31) + c;
        }
        return hash;
    }
}