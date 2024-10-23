/*
 * This is the Stack interface which contains the method that are essential to implement the
 * stack data structure. Their definition will be found in every stack data structure implementation
 */

package com.zilaidawwab.algorithms.datastructures.stack;

public interface Stack<T> {

    // return the number of elements in the stack
    public int size();

    // return if the stack is empty
    public boolean isEmpty();

    // push the element on the stack
    public void push(T element);

    // pop the element off the stack
    public T pop();

    // glance at the last element added
    public T peek();
}
