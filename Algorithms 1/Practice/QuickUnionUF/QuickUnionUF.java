public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int N) {
      id = new int[N];
      // set id of each object to itself
      for (int i = 0; i < N; i++) {
        id[i] = i;
      }
    }

    public int root(int i) {
      // choose parent pointer until reach root
      while (i != id[i]) {
        i = id[i];
      }
      return i;
    }

    public boolean connected(int p, int q) {
      return root(p) == root(q);
    }

    public void union(int p, int q) {
      int i = root(p);
      int j = root(q);
      id[i] = j;
    }
}
