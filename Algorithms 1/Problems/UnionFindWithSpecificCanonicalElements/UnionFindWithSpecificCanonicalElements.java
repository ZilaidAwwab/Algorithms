public class UnionFindWithSpecificCanonicalElements {
    private int[] parent;
    private int[] size;
    private int[] maxElement;

    public UnionFindWithSpecificCanonicalElements(int N) {
      parent = new int[N];
      size = new int[N];
      maxElement = new int[N];

      for (int i = 0; i < N; i++) {
        parent[i] = i;
        size[i] = 1;
        maxElement[i] = i;
      }
    }

    public int find(int p) {
      if (parent[p] != p) {
        parent[p] = find(parent[p]);
      }
      return parent[p];
    }

    public int findMax(int p) {
      int root = find(p);
      return maxElement[root];
    }

    public void union(int p, int q) {
      int rootP = find(p);
      int rootQ = find(q);

      if (rootP == rootQ) return;

      if (size[rootP] < size[rootQ]) {
        parent[rootP] = rootQ;
        size[rootQ] += size[rootP];
        maxElement[rootQ] = Math.max(maxElement[rootQ], maxElement[rootP]);
      } else {
        parent[rootQ] = rootP;
        size[rootP] += size[rootQ];
        maxElement[rootP] = Math.max(maxElement[rootP], maxElement[rootQ]);
      }
    }

    public boolean connected(int p, int q) {
      return find(p) == find(q);
    }

    public static void main(String[] args) {
      int n = 10;
      UnionFindWithSpecificCanonicalElements uf = new UnionFindWithSpecificCanonicalElements(n);
      uf.union(1, 2);
      uf.union(2, 6);
      uf.union(6, 9);

      System.out.println(uf.findMax(1));
      System.out.println(uf.findMax(2));
      System.out.println(uf.findMax(6));
      System.out.println(uf.findMax(9));
    }
}
