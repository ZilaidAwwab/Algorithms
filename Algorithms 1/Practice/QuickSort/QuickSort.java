import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a); // eliminate dependencies on input
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1); // sorting the left part
        sort(a, j + 1, hi); // sorting the right part
    }

    /*
    ***Quick3Way sorting algorithm (An improvment to the quick sort by Dijkstra)***
    
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo+1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        } // Now a[lo...lt-1] < v = a[lt...gt] <a[gt+1...hi]
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }
    */

    private static int partition(Comparable[] a, int lo, int hi) {
        // partition into a[lo...i-1], a[i], a[i+1...hi]
        int i = lo, j = hi + 1; // left and right scan indices
        Comparable v = a[lo];
        while (true) {
            // scan right, scan left, check for scan complete and exchange
            while(less(a[++i], v)) if (i == hi) break;
            while(less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private static <T> void exch(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
