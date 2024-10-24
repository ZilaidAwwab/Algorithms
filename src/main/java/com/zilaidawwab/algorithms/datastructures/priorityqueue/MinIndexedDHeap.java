/*
 * MinIndexedDHeap API
 *
 * Implements a Min-Indexed D-ary Heap, a priority queue structure that allows
 * efficient access, insertion, and deletion of elements while tracking the position 
 * of each key in a custom multi-child tree structure defined by a branching factor, d.
 *
 * size()                   - Returns the number of elements in the heap.
 * isEmpty()                - Checks if the heap is empty.
 * contains(int ki)         - Checks if a key index exists in the heap.
 * peekMinKeyIndex()        - Returns the index of the minimum key in the heap.
 * pollMinIndexKey()        - Removes and returns the minimum key index in the heap.
 * peekMinValue()           - Returns the minimum value in the heap without removing it.
 * pollMinValue()           - Removes and returns the minimum value in the heap.
 * insert(int ki, T value)  - Inserts a key index and value into the heap, maintaining order.
 * valueOf(int ki)          - Retrieves the value associated with a given key index.
 * delete(int ki)           - Deletes the value at a specified key index, maintaining heap order.
 * update(int ki, T value)  - Updates the value associated with a key index and adjusts heap order.
 * decrease(int ki, T value) 
 *                          - Decreases the value of a specified key index if the new value is smaller, and reorders.
 * increase(int ki, T value) 
 *                          - Increases the value of a specified key index if the new value is larger, and reorders.
 * sink(int i)              - Moves an element at index i down the heap to maintain order.
 * swim(int i)              - Moves an element at index i up the heap to maintain order.
 * minChild(int i)          - Finds the minimum child index for a node at index i.
 * swap(int i, int j)       - Swaps the elements at indices i and j in the heap.
 * less(int i, int j)       - Helper function to compare elements at indices i and j; true if i < j.
 * less(Object obj1, Object obj2) 
 *                          - Helper function to compare two objects directly.
 * isNotEmptyOrThrow()      - Checks if the heap is not empty; throws exception if empty.
 * keyExistsAndValueNotNullOrThrow(int ki, Object value)
 *                          - Validates that a key index exists and that a value is not null.
 * keyExistsOrThrow(int ki) - Ensures that a key index exists within the heap.
 * valueNotNullOrThrow(Object value)
 *                          - Ensures that a value is not null.
 * keyInBoundsOrThrow(int ki) 
 *                          - Validates that a key index is within the allowable range.
 * isMinHeap()              - Recursively checks if the heap maintains a min-heap property.
 * isMinHeap(int i)         - Recursively verifies the min-heap property from a given index i.
 *
 * toString()               - Provides a string representation of the heap for debugging.
 */

package com.zilaidawwab.algorithms.datastructures.priorityqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MinIndexedDHeap<T extends Comparable<T>> {
    
    // Current number of elements in the heap
    private int size;

    // Maxmimum number of elements in the heap
    private final int n;

    // The degree of every node in the heap
    private final int d;

    // Lookup arrays to track the parent / child indexes of each node
    private final int[] child, parent;

    // The position map (pm) maps key Indexes (ki) to where the position of that key
    // is represented in the priority queue in the domain [0, size]
    public final int[] pm;

    // The inverse map (im) stores the indexes of the keys in the range [0, size]
    // which make up the priority queue. It should be noted that 'im' and 'pm' are
    // inverse of eachother, so: pm[im[i]] = im[pm[i]] = i
    public final int[] im;

    // The values associated with the keys. It is very important to note that this
    // array is indexed by the key indexes (aka 'ki')
    public final Object[] values;

    // Initialize D-ary heap with a maximum capacity of maxSize
    public MinIndexedDHeap(int degree, int maxSize) {

        if (maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");

        d = Math.max(2, degree);
        n = Math.max(d + 1, maxSize);

        im = new int[n];
        pm = new int[n];
        child = new int[n];
        parent = new int[n];
        values = new Object[n];

        for (int i = 0; i < n; i++) {
            parent[i] = (i - 1) / d; // filling up the parent nodes for quick access
            child[i] = i * d + 1; // filling up the child nodes for quick access
            pm[i] = im[i] = -1; // filling up the pm and im to -1 initially
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int ki) {
        keyInBoundsOrThrow(ki);
        return pm[ki] != -1;
    }

    public int peekMinKeyIndex() {
        isNotEmptyOrThrow();
        return im[0];
    }

    public int pollMinIndexKey() {
        int minKi = peekMinKeyIndex();
        delete(minKi);
        return minKi;
    }

    @SuppressWarnings("unchecked")
    public T peekMinValue() {
        isNotEmptyOrThrow();
        return (T) values[im[0]];
    }

    public T pollMinValue() {
        T minValue = peekMinValue();
        delete(peekMinKeyIndex());
        return minValue;
    }

    public void insert(int ki, T value) {
        if (contains(ki)) throw new IllegalArgumentException("Index already exists: received: " + ki);
        valueNotNullOrThrow(value);
        pm[ki] = size;
        im[size] = ki;
        values[ki] = value;
        swim(size++);
    }

    @SuppressWarnings("unchecked")
    public T valueOf(int ki) {
        keyExistsOrThrow(ki);
        return (T) values[ki];
    }

    @SuppressWarnings("unchecked")
    public T delete(int ki) {
        keyExistsOrThrow(ki);
        final int i = pm[ki];
        swap(i, --size);
        sink(i);
        swim(i);
        T value = (T) values[ki];
        values[ki] = null;
        pm[ki] = -1;
        im[ki] = -1;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T update(int ki, T value) {
        keyExistsAndValueNotNullOrThrow(ki, value);
        final int i = pm[ki];
        T oldValue = (T) values[ki];
        values[ki] = value;
        sink(i);
        swim(i);
        return oldValue;
    }

    // Strictly decrease the value associated with 'ki' to 'value'
    public void decrease(int ki, T value) {
        keyExistsAndValueNotNullOrThrow(ki, value);
        if (less(value, values[ki])) {
            values[ki] = value;
            swim(pm[ki]);
        }
    }

    // Strcitly increase the value associated with 'ki' to 'value'
    public void increase(int ki, T value) {
        keyExistsAndValueNotNullOrThrow(ki, value);
        if (less(values[ki], value)) {
            values[ki] = value;
            sink(pm[ki]);
        }
    }

    // Helper Functions

    private void sink(int i) {
        for (int j = minChild(i); j != -1; ) {
            swap(i, j);
            i = j;
            j = minChild(i);
        }
    }

    private void swim(int i) {
        while (less(i, parent[i])) {
            swap(i, parent[i]);
            i = parent[i];
        }
    }

    // From the parent node at index i find the minimum child below it
    private int minChild(int i) {
        if (i >= size) return -1;
        int index = -1;
        int from = child[i];
        int to = Math.min(size, from + d);

        for (int j = from; j < to; j++) {
            if (less(j, i)) index = i = j;
        }

        return index;
    }

    private void swap(int i, int j) {
        pm[im[j]] = i;
        pm[im[i]] = j;
        int temp = im[i];
        im[i] = im[j];
        im[j] = temp;
    }

    @SuppressWarnings("unchecked")
    private boolean less(int i, int j) {
        return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
    }
    
    @SuppressWarnings("unchecked")
    private boolean less(Object obj1, Object obj2) {
        return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
    }
    
    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) list.add(im[i]);
        return list.toString();
    }

    private void isNotEmptyOrThrow() {
        if (isEmpty()) throw new NoSuchElementException("Priority Queue Underflow");
    }

    private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
        keyExistsOrThrow(ki);
        valueNotNullOrThrow(value);
    }

    private void keyExistsOrThrow(int ki) {
        if (!contains(ki)) throw new NoSuchElementException("Index doesn't exist; received: " + ki);
    }
    
    private void valueNotNullOrThrow(Object value) {
        if (value == null) throw new IllegalArgumentException("Value cannot be null");
    }

    private void keyInBoundsOrThrow(int ki) {
        if (ki < 0 || ki >= n) throw new IllegalArgumentException("Key index out of bound; received: " + ki);
    }

    // Recursively check if the heap is a min heap.
    public boolean isMinHeap() {
        return isMinHeap(0);
    }

    private boolean isMinHeap(int i) {
        int from = child[i];
        int to = Math.min(size, from + d);

        for (int j = from; j < to; j++) {
            if (!less(i, j)) return false;
            if (!isMinHeap(j)) return false;
        }

        return true;
    }
}
