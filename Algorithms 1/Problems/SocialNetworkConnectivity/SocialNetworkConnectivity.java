public class SocialNetworkConnectivity {
  private int[] id;
  private int[] size;
  private int n;

  public SocialNetworkConnectivity(int N) {
    n = N;
    id = new int[N];
    size = new int[N];

    for (int i = 0; i < N; i++) {
      id[i] = i;
      size[i] = 1;
    }
  }

  private int root(int x) {
    while (x != id[x]) x = id[x];
    return x;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q, String timeStamp) {
    int i = root(p);
    int j = root(q);
    if (i == j) return;

    if (size[i] < size[j]) {
      size[i] = j;
      size[j] += size[i];

      if (size[j] == n) {
        System.out.println("All members are connected at Timestamp " + timeStamp);
      }
    } else {
      size[j] = i;
      size[i] = size[j];

      if (size[i] == n) {
        System.out.println("All members are connected at Timestamp " + timeStamp);
      }
    }
  }

  public static void main(String[] args) {
    SocialNetworkConnectivity obj = new SocialNetworkConnectivity(6);
    obj.union(1,5, "2019-08-14 18:00:00");
    obj.union(2,4, "2019-08-14 18:00:01");
    obj.union(1,3, "2019-08-14 18:00:02");
    obj.union(5,2, "2019-08-14 18:00:03");
    obj.union(0,3,"2019-08-14 18:00:04");
    obj.union(2,1,"2019-08-14 18:00:05");
  }
}
