import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ThreeSumFast {
  public static int count(int[] a) {
    // count triples that sum to zero
    Arrays.sort(a);
    int n = a.length;
    int count = 0;

    for (int i = 0; i < n; i++) {
      for (int j = i+1; j < n; j++) {
        if (BinarySearch.rank(-a[i]-a[j], a) > j) {
          count++;
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = In.readInts(args[0]);
    StdOut.println(count(a));
  }
}

// N^2 log N
