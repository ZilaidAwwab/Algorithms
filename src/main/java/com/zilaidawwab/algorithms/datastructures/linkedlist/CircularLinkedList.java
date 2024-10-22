/*
 * CircularLinkedList API
 *
 * This class implements a circular linked list, where the last node points back
 * to the first node, creating a circular structure. It supports standard list
 * operations such as insertion at the head or tail, deletion from the head,
 * and basic utility methods to inspect the list.
 *
 * Node              - Inner static class representing a node in the circular
 *                     linked list, containing data and a reference to the next node.
 *
 * CircularLinkedList constructor
 *                   - Initializes an empty circular linked list with a null last
 *                     node and length of 0.
 *
 * length()          - Returns the number of nodes in the circular linked list, O(1).
 *
 * isEmpty()         - Checks if the list is empty by verifying if the length is 0, O(1).
 *
 * createCircularLinkedList()
 *                   - Creates a pre-defined circular linked list with four nodes
 *                     containing values 1, 7, 12, and 19. The last node points to
 *                     the first, making the list circular.
 *
 * display()         - Traverses and prints the entire circular linked list, starting
 *                     from the first node after the last node. If the list is empty,
 *                     nothing is displayed.
 *
 * insertFirst(int data)
 *                   - Inserts a new node at the beginning of the list. If the list is
 *                     empty, the new node becomes both the first and last node.
 *                     Otherwise, the new node is added before the first node.
 *
 * insertLast(int data)
 *                   - Inserts a new node at the end of the list. If the list is
 *                     empty, the new node becomes both the first and last node.
 *                     Otherwise, it is appended after the current last node and the
 *                     last pointer is updated.
 *
 * removeFirst()     - Removes and returns the first node of the circular linked list.
 *                     If the list contains only one node, the list becomes empty.
 *                     Otherwise, the second node becomes the new first node. Throws
 *                     NoSuchElementException if the list is already empty.
 *
 * main()            - Test cases for creating a circular linked list, inserting at
 *                     the head and tail, displaying the list, and removing the first
 *                     element, with sample output provided.
 */

package com.zilaidawwab.algorithms.datastructures.linkedlist;

import java.util.NoSuchElementException;

public class CircularLinkedList {
    private Node last;
    private int length;

    private static class Node {
        private Node next;
        private final int data;

        public Node(int data) {
            this.data = data;
        }
    }

    public CircularLinkedList() {
        last = null;
        length = 0;
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void createCircularLinkedList() {
        Node first = new Node(1);
        Node second = new Node(7);
        Node third = new Node(12);
        Node fourth = new Node(19);

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;

        last = fourth;
    }

    public void display() {
        if(last == null) return;

        Node first = last.next;
        while (first != last) {
            System.out.print(first.data + " --> ");
            first = first.next;
        }
        System.out.println(first.data);
    }

    public void insertFirst(int data) {
        Node temp = new Node(data);
        if (last == null) {
            last = temp;
            last.next = last;
        } else {
            temp.next = last.next;
            last.next = temp;
        }
        length++;
    }

    public void insertLast(int data) {
        Node temp = new Node(data);
        if (last == null) {
            last = temp;
            last.next = last;
        } else {
            temp.next = last.next;
            last.next = temp;
            last = temp;
        }
        length++;
    }

    public int removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("List is already empty");

        Node temp = last.next;
        int removedData = temp.data;

        if (last.next == last) {
            last = null;
        } else {
            last.next = temp.next; // Update last to point to the new first node
        }
        temp.next = null;
        length--;
        return removedData;
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();

        // Test 1: Create a circular linked list and display it
        cll.createCircularLinkedList();
        System.out.print("Initial Circular Linked List: ");
        cll.display();  // Output: 1 --> 7 --> 12 --> 19 --> 1

        // Test 2: Insert element at the beginning and display
        cll.insertFirst(25);
        System.out.print("After inserting 25 at the start: ");
        cll.display();  // Output: 25 --> 1 --> 7 --> 12 --> 19 --> 25

        // Test 3: Insert element at the end and display
        cll.insertLast(30);
        System.out.print("After inserting 30 at the end: ");
        cll.display();  // Output: 25 --> 1 --> 7 --> 12 --> 19 --> 30 --> 25

        // Test 4: Remove the first element and display
        int removedData = cll.removeFirst();
        System.out.println("Removed first element: " + removedData);  // Output: 25
        System.out.print("After removing the first element: ");
        cll.display();  // Output: 1 --> 7 --> 12 --> 19 --> 30 --> 1

        // Test 5: Remove more elements to check circular functionality
        cll.removeFirst();
        System.out.print("After removing another first element: ");
        cll.display();  // Output: 7 --> 12 --> 19 --> 30 --> 7
    }
}
