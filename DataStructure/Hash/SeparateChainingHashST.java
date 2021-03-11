package Hash;

import edu.princeton.cs.algs4.SequentialSearchST;

/**
 * * Hashing with separate chaining. A hash function converts keys into array indices. The second component of
 * a hashing algorithm is collision resolution: a strategy for handling the case when two or more keys to be
 * inserted hash to the same index. A straightforward approach to collision resolution is to build, for each of
 * the M array indices, a linked list of the key-value pairs whose keys hash to that index. The basic idea is to choose
 * M to be sufficiently large that the lists are sufficiently short to enable efficient search through a two-step
 * process: hash to find the list that could contain the key, then sequentially search through that list for the key.
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key, Value> {
    private int N; // The number of key-values.
    private int M; // The shape of Hash table
    private SequentialSearchST<Key, Value>[] st;  // The array to store the list.

    public SeparateChainingHashST()
    {
        this(997);
    }

    public SeparateChainingHashST(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) st[i] = new SequentialSearchST<Key, Value>();
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7ffffff) % this.M;
    }

    public Value get(Key key){
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value value){
        st[hash(key)].put(key, value);
    }
}
