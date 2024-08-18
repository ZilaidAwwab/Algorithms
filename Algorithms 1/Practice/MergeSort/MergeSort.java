public class MergeSort {
  public static Comparable[] aux;

  public static void sort(Comparable[] a) {
    aux = new Comparable[a.length]; // allocate space just once
    sort(a, 0, a.length - 1);
  }

  /*
  Bottom up merge
  public static Comparable[] aux;

  private static void sort(Comparable[] a) {
    // Do lg N passes of pairwise merges
    int N = a.length;
    aux = new Comparable[N];
    for (int sz = 1; sz < N; sz = sz + sz) {
      for (int lo = 0; lo < N-sz; lo += sz+sz) {
        merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
      }
    }
  }
  */

  private static void sort(Comparable[] a, int lo, int hi) {
    // sort a[lo...hi]
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, lo, mid);
    sort(a, mid+1, hi);
    merge(a, lo, mid, hi);
  }

  private static <T extends Comparable<T>> boolean less(T v, T w) {
    return v.compareTo(w) < 0;
  }

  public static void merge(Comparable[] a, int lo, int mid, int hi) {
    // merge a[lo...mid] with [mid+1...hi]
    int i = lo, j = mid+1;

    for (int k = lo; k <= hi; k++) {
      // copy a[lo...hi] to aux[lo...hi]
      aux[k] = a[k];
    }

    for (int k = lo; k <= hi; k++) {
      // merge back to a[lo...hi]

      /*
      here aux[j++] is a shorthand performing two operations
      1. a[k] = a[j]
      2. j++
      */

      if (i > mid) a[k] = aux[j++];
      else if (j > hi) a[k] = aux[i++];
      else if (less(aux[j], aux[i])) a[k] = aux[j++];
      else a[k] = aux[i++];
    }
  }
}
