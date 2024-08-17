import java.util.Scanner;

public class ShellSort {
  public static <T extends Comparable<T>> void sort(T[] a) {
    // sort a[] in increasing order
    int N = a.length;
    int h = 1;
    while (h < N/3) {
      h = 3*h + 1;
    }
    while (h >= 1) {
      // h sort the array
      for (int i = h; i < N; i++) {
        // insert a[i] among a[i-h], a[i-2*h], a[i-3*h]...
        for (int j = i; j >= h && less(a[j], a[j-h]); j-=h) {
          exch(a, j, j-h);
        }
      }
      h = h/3;
    }
  }

  // This part is same as in selection sort
  private static <T extends Comparable<T>> boolean less(T v, T w) {
    return v.compareTo(w) < 0;
  }

  private static <T> void exch(T[] a, int i, int j) {
    T t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  private static <T> void show(T[] a) {
    // print the array on a single line
    for (int i = 0; i  < a.length; i++) {
      System.out.print(a[i] + " ");
    }
    System.out.println();
  }

  public static <T extends Comparable<T>> boolean isSorted(T[] a) {
    // test whether the array entries are in order
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i-1])) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    // Read the strings from standard input and sort them, then print
    Scanner scanner = new Scanner(System.in);
    String[] a = scanner.nextLine().split(" ");
    sort(a);
    assert isSorted(a);
    show(a);
    scanner.close();
  }
}
