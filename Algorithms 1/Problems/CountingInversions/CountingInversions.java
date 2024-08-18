/*
Question
Counting inversions.
An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j 
but a[i]>a[j]. Given an array, design a linearithmic algorithm to count the 
number of inversions.
 */

public class CountingInversions {
  private int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
    for (int k = lo; k <= hi; k++) {
      aux[k] = a[k];
    }

    int k = lo;
    int i = lo;
    int j = mid + 1;
    int count = 0;

    while (k < hi) {
      if (i > mid) a[k++] = aux[j++];
      else if (j > hi) a[k++] = aux[i++];
      else if (less(aux[j], aux[i])) {
        count += mid + 1 - i;
        a[k++] = aux[j++];
      } else a[k++] = aux[i++];
    }
    return count;
  }

  private static <T extends Comparable<T>> boolean less(T v, T w) {
    return v.compareTo(w) < 0;
  }

  public int countingInversions(Comparable[] a) {
    Comparable[] aux = new Comparable[a.length];
    int count = 0;

    for (int sz = 1; sz < a.length; sz += sz) {
      for (int i = 0; i < a.length - sz; i += 2*sz) {
        int lo = i;
        int m = i + sz - 1;
        int hi = Math.min(i + 2*sz - 1, a.length - 1);
        count += merge(a, aux, lo, m, hi);
      }
    }
    return count;
  }
}
