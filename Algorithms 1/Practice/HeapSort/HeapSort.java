public class HeapSort {

    public static void sort(Comparable[] a) {
        int N = a.length;

        // Build heap (rearrange array)
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }

        // One by one extract an element from heap
        while (N > 1) {
            exch(a, 1, N--); // Move current root to end
            sink(a, 1, N);   // call max heapify on the reduced heap
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        // Sink the element at index k in the heap
        while (2 * k <= N) {
            int j = 2 * k; // Left child index

            // If right child exists and is greater than the left child, use the right child
            if (j < N && less(a, j, j + 1)) j++;

            // If parent is larger than or equal to the largest child, stop sinking
            if (!less(a, k, j)) break;

            // Otherwise, swap and continue sinking
            exch(a, k, j);
            k = j; // Continue to next level
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1]; // Adjust for 0-based indexing
        a[i - 1] = a[j - 1];        // Swap elements
        a[j - 1] = temp;
    }

    private static boolean less(Comparable[] a, int i, int j) {
        // Compare elements at index i-1 and j-1 due to 0-based array indexing
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    public static void main(String[] args) {
        Integer[] a = { 3, 5, 1, 2, 4, 6 };
        sort(a);

        // Print sorted array
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
