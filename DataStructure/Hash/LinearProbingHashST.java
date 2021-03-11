package Hash;

public class LinearProbingHashST<Key, Value> {
    private int N;  // The number of Key-Values
    private int M;  // The number of hash table
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST(){
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int size){
        keys = (Key[]) new Object[size];
        vals = (Value[]) new Object[size];
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7ffffff) % this.M;
    }

    private void resize(int size){
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(size);
        for (int i = 0; i < this.M; i++) {
            if (keys[i] != null) t.put(keys[i], vals[i]);
        }

        keys = t.keys;
        vals = t.vals;
        this.M = t.M;
    };

    public void put(Key key, Value value) {
        if (N >= M / 2) resize(2 * M);

        int i;

        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if(keys[i].equals(key)) {
                vals[i] = value;
                return;
            }
        }

        keys[i] = key;
        vals[i] = value;
        N++;

    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key))
                return vals[i];
        }
        return null;
    }


}
