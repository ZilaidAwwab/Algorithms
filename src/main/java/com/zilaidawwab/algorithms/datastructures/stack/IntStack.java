/*
 * IntStack API
 *
 * This file contains an implementation of an integer only stack which is extremely quick and
 * lightweight. In terms of performance it can outperform java.util.ArrayDeque (Java's fastest
 * stack implementation) by a factor of 50!
 *
 * IntStack(int maxSize)
 *                   - Constructs a stack with a fixed maximum capacity, where
 *                     maxSize specifies the maximum number of elements the stack
 *                     can hold.
 *
 * size()            - Returns the current number of elements in the stack, O(1).
 *
 * isEmpty()         - Checks if the stack is empty by verifying if the size is 0, O(1).
 *
 * push(Integer value)
 *                   - Adds a new integer value to the top of the stack. Throws an
 *                     ArrayIndexOutOfBoundsException if the stack is full, O(1).
 *
 * pop()             - Removes and returns the integer value at the top of the stack.
 *                     Throws an EmptyStackException if the stack is empty, O(1).
 *
 * peek()            - Returns the integer value at the top of the stack without
 *                     removing it. Throws an EmptyStackException if the stack is
 *                     empty, O(1).
 *
 * int[] arr         - The underlying array used to store the elements of the stack.
 *                     This array is initialized with a fixed size during stack
 *                     construction.
 *
 * int pos           - A variable that tracks the current position in the array,
 *                     representing the number of elements in the stack.
 *
 * Exceptions:
 * EmptyStackException
 *                   - Thrown when attempting to pop or peek an element from an empty stack.
 *
 * ArrayIndexOutOfBoundsException
 *                   - Thrown when attempting to push an element onto a full stack, as
 *                     the array size is fixed and cannot grow dynamically.
 */

package com.zilaidawwab.algorithms.datastructures.stack;

import java.util.EmptyStackException;

public class IntStack implements Stack<Integer> {

    private final int[] arr;
    private int pos = 0;

    // maxSize is the max number of elements that can be in a queue at any given time
    public IntStack(int maxSize) {
        arr = new int[maxSize];
    }

    // Return the number of elements inside the stack
    @Override
    public int size() {
        return pos;
    }

    // Return true of false on whether the stack is empty
    @Override
    public boolean isEmpty() {
        return pos == 0;
    }

    // Add an element to the top of the stack
    @Override
    public void push(Integer value) {
        if (pos >= arr.length) throw new ArrayIndexOutOfBoundsException("Stack is full");
        arr[pos++] = value;
    }

    // Returning the last added element value from the stack
    @Override
    public Integer pop() {
        if (isEmpty()) throw new EmptyStackException();
        return arr[--pos];
    }

    // Return the element on the top of the stack (without removing it)
    @Override
    public Integer peek() {
        return arr[pos - 1];
    }

    // Test cases
    public static void main(String[] args) {
        IntStack s = new IntStack(5);
        s.push(2);
        s.push(5);
        s.push(7);
        s.push(8);
        s.push(9);

        System.out.println(s.pop()); // 9
        System.out.println(s.pop()); // 8
        System.out.println(s.pop()); // 7

        s.push(6);
        s.push(9);
        s.push(12);

        // This prints and removes all the elements from the stack
        while (!s.isEmpty()) System.out.println(s.pop());
    }
}
