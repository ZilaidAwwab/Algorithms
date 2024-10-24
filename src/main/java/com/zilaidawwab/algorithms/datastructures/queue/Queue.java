/*
 * Queue API
 *
 * This class implements a simple queue data structure using a singly linked list.
 * The queue supports basic operations such as enqueue (adding elements), dequeue
 * (removing elements), checking the queue's length, and printing its contents.
 *
 * Node               - Inner static class representing a node in the queue. Each node
 *                      contains an integer value (data) and a reference to the next node.
 *
 * Queue()            - Constructs an empty queue with no elements.
 *
 * length()           - Returns the current number of elements in the queue, O(1).
 *
 * isEmpty()          - Checks if the queue is empty by verifying if the length is 0, O(1).
 *
 * enqueue(int data)  - Adds a new node with the given value to the rear of the queue.
 *                      If the queue is empty, the new node becomes the front as well as the rear, O(1).
 *
 * dequeue()          - Removes the front element from the queue and updates the front to
 *                      the next node. Throws a NoSuchElementException if the queue is empty, O(1).
 *
 * print()            - Prints all elements of the queue in order, starting from the front
 *                      to the rear. If the queue is empty, nothing is printed, O(n), where n
 *                      is the number of elements in the queue.
 *
 * Exceptions:
 * NoSuchElementException
 *                    - Thrown when attempting to dequeue from an empty queue.
 */

package com.zilaidawwab.algorithms.datastructures.queue;

import java.util.NoSuchElementException;

public class Queue {

    private Node front;
    private Node rare;
    private int length;

    private static class Node {
        private final int data; // can be a generic type as well
        private Node next; // reference to the next node in the list

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Queue() {
        front = null;
        rare = null;
        length = 0;
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length() == 0;
    }

    public void enqueue(int data) {
        Node temp = new Node(data);
        if (isEmpty()) front = temp;
        else rare.next = temp;

        rare = temp;
        length++;
    }

    public int dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is already empty");

        int result = front.data;
        front = front.next;

        if (front == null) rare = null;
        length--;
        return result;
    }

    public void print() {
        if (isEmpty()) return;
        Node current = front;
        while (current != null) {
            System.out.print(current.data + " --> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue(21);
        queue.enqueue(45);
        queue.enqueue(191);

        queue.print();

        queue.dequeue();
        queue.dequeue();

        queue.print();
    }
}
