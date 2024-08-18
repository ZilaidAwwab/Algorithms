/*
Question
Merging with smaller auxiliary array.
Suppose that the subarray a[0] to a[n−1] is sorted and the subarray a[n] to a[2∗n−1] 
is sorted. How can you merge the two subarrays so that a[0] to a[2∗n−1] is sorted 
using an auxiliary array of length n (instead of 2n)?
*/

public class MergingWithSmallerAuxiliaryArray {
  private boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }

  public void mergeWithSmaller(Comparable[] a, Comparable[] aux) {
    int N = aux.length;
    assert a.length == 2*N;

    for (int i = 0; i < N; i++) {
      aux[i] = a[i];
    }

    int l = 0;
    int r = N;

    int i = 0;
    for (; i < N; i++) {
      if (less(aux[l], a[r])) a[i] = aux[l++];
      else a[i] = a[r++];
    }

    while (l < N) {
      if (r >= 2*N || less(aux[l], a[r])) a[i++] = aux[l++];
      else a[i++] = a[r++];
    }
  }
}
