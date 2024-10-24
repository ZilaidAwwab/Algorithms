/*
 * BinaryHeapQuickRemovals API
 *
 * This class provides a min-priority queue implementation using a binary heap with
 * quick removal support. Each element's indices are tracked within a hash map,
 * allowing efficient removal and containment checks.
 *
 * BinaryHeapQuickRemovals()               - Constructs an initially empty priority queue.
 * BinaryHeapQuickRemovals(int size)       - Constructs a priority queue with a given initial capacity.
 * BinaryHeapQuickRemovals(T[] elements)   - Constructs a priority queue from an array using O(n) heapify.
 * BinaryHeapQuickRemovals(Collection<T>)  - Constructs a priority queue from a collection.
 *
 * size()                                  - Returns the current size of the heap.
 * isEmpty()                               - Checks if the queue is empty.
 * clear()                                 - Clears the heap and map, O(n) time complexity.
 * peek()                                  - Returns the element with the least priority in the queue, or null if empty.
 * poll()                                  - Removes and returns the root of the heap, O(log n).
 * contains(T element)                     - Checks if an element exists in the heap, O(1).
 * add(T element)                          - Adds an element to the priority queue, O(log n).
 * remove(T element)                       - Removes a specified element from the heap, O(log n).
 * removeAt(int i)                         - Removes the node at a given index, O(log n).
 * isMinHeap(int k)                        - Validates whether the heap satisfies the min-heap invariant.
 * toString()                              - Provides a string representation of the heap.
 *
 * Private helper methods:
 * - less(int i, int j)                    - Compares two nodes to maintain the heap invariant.
 * - swim(int k)                           - Maintains the min-heap property by "swimming" up, O(log n).
 * - sink(int k)                           - Maintains the min-heap property by "sinking" down, O(log n).
 * - swap(int i, int j)                    - Swaps two nodes and updates the map for indices.
 * - mapAdd(T value, int index)            - Adds a node's value and index to the map.
 * - mapRemove(T value, int index)         - Removes a specific index of a value from the map, O(log n).
 * - mapGet(T value)                       - Retrieves an index for a given value if it exists in the heap.
 * - mapSwap(T val1, T val2, int idx1, int idx2)
 *                                         - Swaps index mappings for two values.
 */

package com.zilaidawwab.algorithms.datastructures.priorityqueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BinaryHeapQuickRemovals<T extends Comparable<T>> {

    // A dynamic array to keep track of the elements inside the heap
    private List<T> heap = null;

    // This map keeps track of the possible indices at a node value if found in the heap
    // Having this mapping lets us have O(log n), removals and O(1) containment check at a cost of some additional space and minor overhead
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    // Construct an initially empty priority queue
    public BinaryHeapQuickRemovals() {
        this(1);
    }

    // Construct a priority queue with an initial capacity
    public BinaryHeapQuickRemovals(int size) {
        heap = new ArrayList<>(size);
    }

    // Construct a priority queue using heapify in O(n) time
    public BinaryHeapQuickRemovals(T[] elements) {
        int heapSize = elements.length;
        heap = new ArrayList<>(heapSize);

        // Place all elements in heap
        for (int i = 0; i < heapSize; i++) {
            // Adding the index of elements array into the index of hash map
            mapAdd(elements[i], i);
            heap.add(elements[i]);
        }

        // Heapify process, O(n)
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);
    }

    // Priority queue construction
    public BinaryHeapQuickRemovals(Collection<T> elements) {
        this(elements.size());
        for (T element: elements) add(element);
    }

    // Returns the size of the heap
    public int size() {
        return heap.size();
    }

    // Check if the queue is empty or not
    public boolean isEmpty() {
        return size() == 0;
    }

    // Clear everything inside the heap O(n)
    public void clear() {
        heap.clear();
        map.clear();
    }

    // Return the value of the element with the least priority in the priority queue (return null if empty)
    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    // Removes the root of the heap O(log n)
    public T poll() {
        return removeAt(0);
    }

    // Test if an element is in the heap O(1)
    public boolean contains(T element) {
        // Map lookup to check containment, O(1)
        if (element == null) return false;
        return map.containsKey((element));

        // Linear scan to check containment, O(n)
        // for (int i = 0; i < heapSize; i++) if (heap.get(i).equals(element)) return true;
        // return false;
    }

    // Add an element to the priority queue, O(log n)
    public void add(T element) {
        if (element == null) throw new IllegalArgumentException();
        heap.add(element);
        int indexOfLastElement = size() - 1;
        mapAdd(element, indexOfLastElement);
        swim(indexOfLastElement);
    }

    // Test if the values of node i <= node j
    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    // Perform bottom up node swim, O(log n)
    private void swim(int k) {
        // Grab the index of the parent node WRT to k
        int parent = (k - 1) / 2;

        // Keep swimming until we haven't reached the root and while we're less than our parent
        while (k > 0 && less(k, parent)) {
            // Exchange k with parent
            swap(parent, k);
            k = parent;
            // Grab the index of the next parent wrt k
            parent = (k - 1) / 2;
        }
    }

    // Top down node sink, O(log n)
    private void sink(int k) {
        int heapSize = size();
        while (true) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int smallest = left;
            if (right < heapSize && less(right, left)) smallest = right;

            if(left >= heapSize || less(k, smallest)) break;

            swap(smallest, k);
            k = smallest;
        }
    }

    // Swap 2 nodes
    private void swap(int i, int j) {
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);

        mapSwap(elem_i, elem_j, i, j);
    }

    // Remove a particular element in the heap, O(log n)
    public boolean remove(T element) {
        if (element == null) return false;

        // Linear removal via search, O(n)
        /*
        for (int i = 0; i < heap.size(); i++) {
            if (element.equals(heap.get(i))) {
                removeAt(i);
                return true;
            }
        }
        */

        // Logarithmic removal via map, O(log n)
        Integer index = mapGet(element);
        if (index != null) removeAt(index);
        return index != null;
    }

    // Removes a node at a particular index, O(log n)
    private T removeAt(int i) {
        if (isEmpty()) return null;

        int indexOfLastElement = size() - 1;
        T removeData = heap.get(i);
        swap(i, indexOfLastElement);

        // Remove the value
        heap.remove(indexOfLastElement);
        mapRemove(removeData, indexOfLastElement);

        // Remove last element
        if (i == indexOfLastElement) return removeData;

        T element = heap.get(i);
        sink(i);

        if (heap.get(i).equals(element)) swim(i);
        return removeData;
    }

    public boolean isMinHeap(int k) {
        // If we are outside the bound of the heap return true
        int heapSize = size();
        if (k >= heapSize) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        // Make sure that the current node is less than its left and right node (if they are)
        // return false otherwise to indicate its invalid
        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // Recurse on both children to make sure that they are valid heap
        return isMinHeap(left) && isMinHeap(right);
    }

    // Add a node value and its index to the map
    private void mapAdd(T value, int index) {
        TreeSet<Integer> set = map.get(value);

        // New value been inserted in the map
        if (set == null) {
            set = new TreeSet<>();
            set.add(index);
            map.put(value, set);
            // Value already exist in the map
        } else set.add(index);
    }

    // Removes the index at a given value, O(log n)
    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        if (set == null) return;

        set.remove(index); // takes O(log n) removal time
        if (set.isEmpty()) map.remove(value);
    }

    // Extract an index position for a given value
    // NOTE: If the value exist multiple times in the heap, the highest index is returned (This is arbitrarily chosen)
    private Integer mapGet(T value) {
        TreeSet<Integer> set = map.get(value);
        if (set != null) return set.last();
        return null;
    }

    // Exchange the index of 2 nodes internally within a map
    private void mapSwap(T val1, T val2, int val1Index, int val2Index) {
        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        set1.remove(val1Index);
        set2.remove(val2Index);

        set1.add(val2Index);
        set2.add(val1Index);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
