import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;

public class TwoSumFast {
  public static int count(int[] a) {
    // count pairs that sum to zero
    Arrays.sort(a);
    int n = a.length;
    int count = 0;

    for (int i = 0; i < n; i++) {
      if (BinarySearch.rank(-a[i], a) > i) count++;
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = {-10, -1, 0, 1, 2, 3, 4, 5, 6, 10 };
    StdOut.println(count(a));
  }
}

// Linearithmic time log n
