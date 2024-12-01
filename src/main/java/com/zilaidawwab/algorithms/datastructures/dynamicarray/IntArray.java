/*
 * Dynamic Array API
 * 
 * This class implements a dynamic integer array, providing common operations 
 * such as insertion, deletion, resizing, and more. Below are the supported APIs:
 * 
 * size()          - Returns the current number of elements in the array.
 * isEmpty()       - Returns a boolean indicating whether the array is empty.
 * get(int index)  - Returns the element at the specified index.
 * set(int index, int element)  
 *                 - Sets the value at the specified index to the given element.
 * add(int element)
 *                 - Adds the given element to the end of the array. Resizes 
 *                   the array if necessary.
 * removeAt(int index)
 *                 - Removes the element at the specified index, shifting the 
 *                   subsequent elements to the left.
 * remove(int element)
 *                 - Removes the first occurrence of the specified element 
 *                   (if present) and returns a boolean indicating success.
 * reverse()       - Reverses the order of the elements in the array.
 * binarySearch(int key)
 *                 - Performs a binary search for the given key in the sorted 
 *                   portion of the array. Returns the index if found, else -1.
 * sort()          - Sorts the elements of the array in ascending order.
 * iterator()      - Returns an iterator to traverse the elements of the array.
 * toString()      - Returns a string representation of the array in the format [elem1, elem2, ...].
 */

package com.zilaidawwab.algorithms.datastructures.dynamicarray;

import java.util.Arrays;
import java.util.Iterator;

public class IntArray implements Iterable<Integer> {

    /**
     * private → The variable DEFAULT_CAP is only accessible within the class where it is declared.
     * static → The variable belongs to the class itself rather than any instance of the class.
     * final → The value of DEFAULT_CAP cannot be changed once assigned.
     * int DEFAULT_CAP → The variable DEFAULT_CAP is of type int (integer).
     * 1 << 3 → This is a bitwise left shift operation, meaning:
     * 1 in binary is 00000001
     * Left shifting (<<) by 3 positions results in 00001000, which is 8 in decimal.
     */
    private static final int DEFAULT_CAP = 1 << 3;

    public int[] arr;
    public int len = 0;
    private int capacity = 0;

    // Initializing array with the default capacity
    public IntArray() {
        this(DEFAULT_CAP);
    }

    // Initializing the array with a certain capacity
    public IntArray(int capacity) {
        if(capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = new int[capacity];
    }

    // Given an array, make it dynamic array
    public IntArray(int[] array) {
        if(array == null) throw new IllegalArgumentException("Array cannot be null");
        arr = Arrays.copyOf(array, array.length);
        capacity = len = array.length;
    }

    // Returns the size of the array
    public int size() {
        return len;
    }

    // Return whether the array is empty or not
    public boolean isEmpty() {
        return len == 0;
    }

    // Getter and setter
    public int get(int index) {
        return arr[index];
    }

    public void set(int index, int element) {
        arr[index] = element;
    }

    // Add an element to this dynamic array
    public void add(int element) {
        if(len + 1 >= capacity) {
            if(capacity == 0) capacity = 1;
            else capacity *= 2; // doubling the size of the array
            arr = Arrays.copyOf(arr, capacity);
        }
        arr[len++] = element; // adding the element to the end of the array
    }

    // Removes the element at a specified index in the array
    public void removeAt(int rm_index) {
        // This statement says to start copying elements from destPos and paste them on srcPos, and the number of
        // elements to be copied are equal to length(last arg)
        System.arraycopy(arr, rm_index + 1, arr, rm_index, len - rm_index - 1);
        --len;
        --capacity;
    }

    // Search and remove an element if it is found in the array
    public boolean remove(int element) {
        for(int i = 0; i < len; i++) {
            if(arr[i] == element) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    // Reverse the array
    public void reverse() {
        for(int i = 0; i < len / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[len - i - 1];
            arr[len - i - 1] = tmp;
        }
    }

    // Searching for an element (using binary search on a sorted array)
    public int binarySearch(int key) {
        int index = Arrays.binarySearch(arr, 0, len, key);
        return index;
    }

    // sort the array
    public void sort() {
        Arrays.sort(arr, 0, len);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int index = 0;

            public boolean hasNext() {
                return index < len;
            }

            public Integer next() {
                return arr[index++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        if(len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for(int i = 0; i < len - 1; i++) sb.append(arr[i] + ", ");
            return sb.append(arr[len - 1] + "]").toString();
        }
    }

    public static void main(String[] args) {
        IntArray ar = new IntArray(50);
        ar.add(3);
        ar.add(7);
        ar.add(5);
        ar.add(-2);

        ar.sort(); // [-2, 3, 5, 7]

        for(int i = 0; i < ar.size(); i++) System.out.println(ar.get(i));

        System.out.println(ar);
    }
}
