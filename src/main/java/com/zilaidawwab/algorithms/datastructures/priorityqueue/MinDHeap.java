/*
 * MinDHeap API
 *
 * This class implements a Min-D-ary Heap, a type of priority queue with a customizable
 * branching factor, d. Each node can have up to d children, providing flexible heap 
 * structures with varying numbers of children per node.
 *
 * size()            - Returns the number of elements in the heap.
 * isEmpty()         - Checks if the heap is empty.
 * clear()           - Clears all elements in the heap.
 * peek()            - Retrieves the minimum element in the heap without removing it.
 * poll()            - Removes and returns the minimum element in the heap.
 * add(T element)    - Adds an element to the heap, preserving heap order.
 * sink(int i)       - Moves an element at index i down the heap to maintain order.
 * swim(int i)       - Moves an element at index i up the heap to maintain order.
 * minChild(int i)   - Finds and returns the index of the smallest child for a node at index i.
 * less(int i, int j) 
 *                   - Helper method to compare the elements at indices i and j, returning true if the element at i is smaller.
 * swap(int i, int j) 
 *                   - Swaps the elements at indices i and j in the heap.
 */

package com.zilaidawwab.algorithms.datastructures.priorityqueue;

@SuppressWarnings("unchecked")
public class MinDHeap<T extends Comparable<T>> {
    
    private T[] heap;

    // Here d means degree, meaning that every node can have d children
    // n is the maximum capacity of the heap
    // size is the current size of the heap
    private int d, n, size;

    public MinDHeap(int degree, int maxNodes) {
        d = Math.max(2, degree);
        n = Math.max(d, maxNodes);

        heap = (T[]) new Comparable[n];
    }

    // Returns the number of elements currently present inside the PQ
    public int size() {
        return size;
    }

    // Return true / false depending upon whether the PQ is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Clear all the elements inside the PQ
    public void clear() {
        java.util.Arrays.fill(heap, null);
        size = 0;
    }

    // Returns the element at the top of PQ, or null if the PQ is empty
    public T peek() {
        return isEmpty() ? null : heap[0];
    }

    // Polls an element from the PQ (return null if PQ is empty)
    public T poll() {
        if (isEmpty()) return null;
        T root = heap[0];
        size--;
        heap[0] = heap[size];
        heap[size] = null;
        sink(0);
        return root;
    }

    // Add a non-null element to the PQ
    public void add(T element) {
        if (element == null) throw new IllegalArgumentException("Null element can't be inserted");
        heap[size] = element;
        swim(size);
        size++;
    }

    private void sink(int i) {
        for (int j = minChild(i); j != -1; ) {
            swap(i, j);
            i = j;
            j = minChild(i);
        }
    }

    private void swim(int i) {
        // Here we are computing the parent with (i-1) / d
        while (i > 0 && less(i, (i - 1) / d)) {
            swap(i, (i - 1) / d);
            i = (i - 1) / d;
        }
    }

    // From the parent node at index i find the minimum child below it
    private int minChild(int i) {
        int index = -1;
        int from = i * d + 1; // Computing child with i * d + 1
        int to = Math.min(size, from + d);

        for (int j = from; j < to; j++) {
            if (index == -1 || less(j, index)) index = j;
        }

        return index;
    }

    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
