import java.util.Scanner;

public class SelectionSort {
  public static <T extends Comparable<T>> void sort(T[] a) {
    // sort a[] into decreasing order
    int N = a.length;
    for (int i = 0; i < N; i++) {
      // exchanging i with the smallest entry in a[i+1...N]
      int min = i;
      for (int j = i+1; j < N; j++) {
        if (less(a[j], a[min])) min = j;
      }
      exch(a, i, min);
    }
  }

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
