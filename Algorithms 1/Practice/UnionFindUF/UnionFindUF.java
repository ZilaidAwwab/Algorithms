import java.util.Scanner;

public class UnionFindUF {
    private int[] id;
    private int count;

    public UnionFindUF(int N) {
      // initialize the component id array
      count = N;
      id = new int[N];
      for (int i = 0; i < N; i++) {
        id[i] = i;
      }
    }

    public int count() {
      return count;
    }

    public boolean connected(int p, int q) {
      return find(p) == find(q);
    }

    // Quick Find
    public int find(int p) {
      return id[p]; // N time to access
    }

    // N+3 to 2N+1 time array accesses to combine components
    public void union(int p, int q) {
      // putting p and q in the same component
      int pid = find(p);
      int qid = find(q);

      // nothing to do if p and q are in same component
      if (pid == qid) return;

      // rename p's component to q's name
      for (int i = 0; i < id.length; i++) {
        if (id[i] == pid) id[i] = qid;
      
      // Here count represents the number of connections in the whole network
      // when we initialized, nothing was connected that's why there were N num of connections, but as they get connected, the connects are reduced
      count--;
      }
    }

    /*
    // Quick Union
    private int find(int p) {
      // find component name
      while(p != id[p]) p = id[p];
      return p;
    }

    public void union(int p, int q) {
      // Give p and q the same root
      int pRoot = find(p);
      int qRoot = find(q);
      if (pRoot == qRoot) return;

      id[pRoot] == qRoot;

      count--;
    }
    */

    public static void main(String[] args) {
      // solve dynammic connectivity problem on StdIn
      Scanner scanner = new Scanner(System.in);
      int N = scanner.nextInt();
      UnionFindUF unionFind = new UnionFindUF(N);

      while (scanner.hasNextInt()) {
        int p = scanner.nextInt();
        int q = scanner.nextInt();

        // checking if they are connected
        if (unionFind.connected(p, q)) continue;

        // connecting if not already
        unionFind.union(p, q);

        // printing the connected
        System.out.println(p + " " + q);
      }

      scanner.close();
    }
}
