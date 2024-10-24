/*
 * BinaryHeap API
 *
 * This class implements a binary heap, which can function as a priority queue.
 * Supported operations include insertion, deletion, heapification, and other
 * common heap functionalities. Below are the supported APIs:
 *
 * BinaryHeap()                    - Initializes an empty binary heap with default capacity.
 * BinaryHeap(int size)            - Initializes an empty binary heap with a specified initial capacity.
 * BinaryHeap(T[] elements)        - Builds a binary heap from an array of elements in O(n) time.
 * BinaryHeap(Collection<T> elements) - Builds a binary heap from a collection of elements in O(n) time.
 *
 * size()          - Returns the number of elements in the heap.
 * isEmpty()       - Checks if the heap is empty.
 * clear()         - Empties the heap.
 * peek()          - Retrieves the element with the highest priority without removing it.
 * poll()          - Removes and returns the root element of the heap.
 * contains(T element)
 *                 - Checks if the given element exists in the heap.
 * add(T element)  - Adds an element to the heap, ensuring proper order.
 * less(int i, int j)
 *                 - Helper method to check if the element at index i has lower or equal priority compared to the element at index j.
 * swim(int k)     - Moves an element at index k up the heap to maintain heap order.
 * sink(int k)     - Moves an element at index k down the heap to maintain heap order.
 * swap(int i, int j)
 *                 - Swaps elements at indices i and j.
 * remove(T element)
 *                 - Removes the specified element from the heap if present.
 * removeAt(int i) - Removes an element at a specific index.
 * isMinHeap(int k)
 *                 - Validates that the heap property holds starting from index k.
 * toString()      - Returns a string representation of the heap.
 */

package com.zilaidawwab.algorithms.datastructures.priorityqueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    // A dynamic list to track the elements inside the heap
    private List<T> heap = null;

    // Constructing a priority queue (initially empty)
    public BinaryHeap() {
        this(1);
    }

    // Construct a priority queue with an initial capacity
    public BinaryHeap(int size) {
        heap = new ArrayList<>(size);
    }

    // Constructing a priority queue using heapify in O(n) time
    public BinaryHeap(T[] elements) {
        int heapSize = elements.length;
        heap = new ArrayList<>(heapSize);

        // Place all the elements in the heap
        for (int i = 0; i < heapSize; i++) heap.add(elements[i]);
        // Another way of carrying out the same operation as above
        // heap.addAll(Arrays.asList(elements).subList(0, heapSize));

        // Heapify process
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);
    }

    // Priority queue construction, O(n)
    public BinaryHeap(Collection<T> elements) {
        int heapSize = elements.size();
        heap = new ArrayList<>(heapSize);

        // Add all elements of the given collection to the heap
        heap.addAll(elements);

        // Heapify process
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);
    }

    // Returns the size of the heap
    public int size() {
        return heap.size();
    }

    // Return true / false depending upon whether the heap is empty or not
    public boolean isEmpty() {
        return size() == 0;
    }

    // Clear everything inside the heap, O(n)
    public void clear() {
        heap.clear();
    }

    // Return the value of the element with the least priority in the priority queue (return null if empty)
    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0); // getting the value of the 0th index, which is the least value
    }

    // Removes the root of the heap, O(log n)
    public T poll() {
        return removeAt(0);
    }

    // Test if an element is in the heap, O(log n)
    public boolean contains(T element) {
        // Linear scan
        for (int i = 0; i < size(); i++) {
            if (heap.get(i).equals(element)) return true;
        }
        return false;
    }

    // Adds an element to the priority queue, the element must not be null. O(log n)
    public void add(T element) {
        if (element == null) throw new IllegalArgumentException();
        heap.add(element);
        int indexOfLastElement = size() - 1; // since the array is 0 based
        swim(indexOfLastElement);
    }

    // Test if the value of node i <= node j
    // This method assumes that i and j are valid indices, O(1)
    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);

        // If node1 < node2 (returns -1), node1 == node2 (returns 0), node1 > node2 (returns 1)
        return node1.compareTo(node2) <= 0;
    }

    // Perform bottom up node swim, O(log n)
    private void swim(int k) {
        // Grab the index of the parent node of k
        int parent =  (k - 1) / 2; // Here we are using the 0 based index, that's why k-1 was used

        // Keep swimming while we have not reached the root, and while we are less than our parent
        while (k > 0 && less(k, parent)) {
            // Exchange k with parents
            swap(parent, k);
            k = parent;
            // Grab the index of the next parent WRT node k
            parent = (k - 1) / 2;
        }
    }

    // Top down node sink, O(log n)
    private void sink(int k) {
        int heapSize = size();
        while (true) {
            int left = 2 * k + 1; // left node
            int right = 2 * k + 2; // right node
            int smallest = left; // assuming left is the smallest node of the two children

            // Find which one is smaller, if right set that to smallest
            if (right < heapSize && less(right, left)) smallest = right;

            // Stop if we are outside the bound of the tree, or stop early if we can't sink k anymore
            if (left >= heapSize || less(k, smallest)) break; // if k is smaller than smallest (in the 2nd condition)

            // Move down tree following the smallest node
            swap(smallest, k);
            k = smallest;
        }
    }

    // Swap two nodes, assume i and j are valid, O(1)
    private void swap(int i, int j) {
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);
    }

    // Remove a particular element in the heap, O(n)
    public boolean remove(T element) {
        if (element == null) return false;

        // Linear removal via search, O(n)
        for (int i = 0; i < size(); i++) {
            if (element.equals(heap.get(i))) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    // Removes a node at a particular index
    private T removeAt(int i) {
        if (isEmpty()) return null;

        int indexOfLastElement = size() - 1;
        T removeData = heap.get(i);
        swap(i, indexOfLastElement); // Swapping the element to be removed with the last element

        // removing the last element of the heap
        heap.remove(indexOfLastElement);

        // Check if the last element was removed
        if (i == indexOfLastElement) return removeData;
        T elem = heap.get(i);

        // Try sinking element
        sink(i);

        // If sinking didn't work try swimming
        if (heap.get(i).equals(elem)) swim(i);
        return removeData;
    }

    // Recursively check if this heap is a min heap
    public boolean isMinHeap(int k) {
        // If we are outside the bounds of the heap return true
        int heapSize = size();
        if (k >= heapSize) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        // Make sure that the current node is less than both of its children (if they exist)
        // return false otherwise to indicate that its invalid heap
        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // recurse of both children to make sure they're also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
