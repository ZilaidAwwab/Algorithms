public class QuickUnionUF {
  private int[] id;

  public QuickUnionUF(int N) {
    id = new int[N];
    // set id of each object to itself
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  private int root(int i) {
    // choose parent pointer until reach root
    while (i != id[i]) {
      i = id[i];
    }
    return i;
  }

  public boolean connected(int p, int q) {
    // check if p and q have same roots
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    // change root of p to point to root of q
    int i = root(p);
    int j = root(q);
    id[i] = j;
  }
}
