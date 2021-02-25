package DisjointSET2;

public class WeightedQuickUniosCompress implements DisjointSets{

    private int[] parent;
    private int[] size;

    public WeightedQuickUniosCompress(int N) {
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
            size[i] = 1;
        }
    }

    private int find(int p) {
        int q = p;
        while (parent[p] >= 0) {
            p = parent[p];
        }
        // The magic of compression is here.
        while (parent[q] >= 0) {
            int temp = parent[q];
            parent[q] = p;
            q = temp;
        }
        return p;
    }

    public void connect(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if (size[i] < size[j]) {
            parent[i] = j;
            size[j] += size[i];
        } else {
            parent[j] = i;
            size[i] += size[j];
        }
    }

    public boolean isConnected(int p, int q) {

        return find(p) == find(q);
    }
}
