/*
 * ArrayStack API
 *
 * This class implements a generic stack using a dynamically resizable array to
 * store elements. The stack supports basic operations such as push, pop, peek,
 * and size, and it grows its capacity as needed.
 *
 * ArrayStack()      - Constructs an empty stack with an initial capacity of 16.
 *                     The stack automatically grows as more elements are pushed.
 *
 * size()            - Returns the current number of elements in the stack, O(1).
 *
 * isEmpty()         - Checks if the stack is empty by verifying if the size is 0, O(1).
 *
 * push(T element)   - Adds a new element to the top of the stack. If the underlying
 *                     array is full, the stack's capacity is doubled before adding
 *                     the element, O(1) on average.
 *
 * pop()             - Removes and returns the element at the top of the stack.
 *                     Throws an EmptyStackException if the stack is empty, O(1).
 *
 * peek()            - Returns the element at the top of the stack without removing it.
 *                     Throws an EmptyStackException if the stack is empty, O(1).
 *
 * increaseCapacity() - A private helper method that doubles the capacity of the
 *                     underlying array when the stack is full, O(n), where n is the
 *                     current capacity.
 *
 * Object[] data     - The underlying array that stores the elements of the stack.
 *                     This array grows as needed when more elements are pushed.
 *
 * int size          - Tracks the number of elements currently in the stack.
 *
 * int capacity      - The current capacity of the underlying array. It starts at 16
 *                     and doubles each time the array needs to grow.
 *
 * Exceptions:
 * EmptyStackException
 *                   - Thrown when attempting to pop or peek an element from an empty stack.
 */

package com.zilaidawwab.algorithms.datastructures.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T> {

    private int size;
    private int capacity;
    private Object[] data;

    public ArrayStack() {
        capacity = 16;
        data = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(T element) {
        if (size == capacity) increaseCapacity();
        data[size++] = element;
    }

    // Increase the capacity of the array
    private void increaseCapacity() {
        capacity *= 2;
        data = Arrays.copyOf(data, capacity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T element = (T) data[--size];
        data[size] = null;
        return element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return (T) data[size - 1];
    }
}
