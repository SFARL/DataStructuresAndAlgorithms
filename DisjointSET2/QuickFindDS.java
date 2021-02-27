package DisjointSET2;
public class QuickFindDS implements DisjointSets {

    private int[] id;

    /* Θ(N) */
    public QuickFindDS(int N){
        id = new int[N];
        for (int i = 0; i < N; i ++) {
            id[i] = i;
        }
    }

    /* Θ(N) */
    public void connect(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        // It need to change all the pid to qic
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    /* Θ(1) */
    public boolean isConnected(int p, int q){
        return (id[p] == id[q]);
    }

}
