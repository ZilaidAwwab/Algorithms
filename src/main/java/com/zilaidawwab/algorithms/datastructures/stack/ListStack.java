/*
 * ListStack API
 *
 * This class implements a stack data structure using a linked list as the underlying
 * storage. The stack follows the Last In, First Out (LIFO) principle, where elements
 * are added and removed from the top of the stack. It supports basic stack operations
 * such as push, pop, peek, search, and iteration.
 *
 * ListStack()       - Constructs an empty stack.
 *
 * ListStack(T firstElement)
 *                   - Constructs a stack with a single initial element, which is
 *                     pushed onto the stack.
 *
 * size()            - Returns the number of elements currently in the stack, O(1).
 *
 * isEmpty()         - Checks if the stack is empty by verifying if the size is 0, O(1).
 *
 * push(T element)   - Adds a new element to the top of the stack, O(1).
 *
 * pop()             - Removes and returns the element at the top of the stack.
 *                     Throws an EmptyStackException if the stack is empty, O(1).
 *
 * peek()            - Returns the element at the top of the stack without removing it.
 *                     Throws an EmptyStackException if the stack is empty, O(1).
 *
 * search(T element) - Searches for the given element in the stack, starting from the
 *                     top. Returns the 0-based index of the element from the top of
 *                     the stack, or -1 if the element is not found, O(n).
 *
 * iterator()        - Returns an iterator to allow traversal of the stack elements in
 *                     LIFO order, starting from the bottom. Useful for iterating
 *                     through all elements of the stack, O(n).
 *
 * LinkedList<T>     - The underlying data structure used to implement the stack. It
 *                     allows for efficient insertion and removal of elements at both
 *                     ends.
 *
 * Exceptions:
 * EmptyStackException
 *                   - Thrown when attempting to pop or peek an element from an empty stack.
 *
 * Iterable<T>       - The stack implements the Iterable interface, enabling it to be
 *                     used in enhanced for loops and allowing stack elements to be
 *                     traversed.
 */

package com.zilaidawwab.algorithms.datastructures.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class ListStack<T> implements Iterable<T>, Stack<T> {

    private final LinkedList<T> list = new LinkedList<>();

    // Create an empty stack
    public ListStack() {}

    // Create a stack with an initial element
    public ListStack(T firstElement) {
        push(firstElement);
    }

    // Return the number of elements in the stack
    public int size() {
        return list.size();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Push an element on the stack
    @Override
    public void push(T element) {
        list.addLast(element);
    }

    // Pop an element off the stack (Throw an error if the stack is empty)
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    // Peek the top of the stack without removing the element (throw exception if empty)
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return list.peekLast();
    }

    // Searching for an element starting from the top of the stack (return -1 if not present)
    public int search(T element) {
        return list.lastIndexOf(element);
    }

    // Iterating through the stack, using iterator
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
