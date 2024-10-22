/*
 * DoublyLinkedList API
 *
 * This class implements a generic doubly linked list, providing basic operations
 * such as insertion, deletion, searching, and more. It supports bidirectional
 * traversal of nodes and efficient removal or addition at both ends.
 *
 * Node<T>           - Inner static class representing a node in the doubly
 *                     linked list, containing data and references to the next
 *                     and previous nodes.
 *
 * clear()           - Empties the linked list by unlinking all nodes, O(n).
 *
 * size()            - Returns the number of nodes present in the linked list.
 *
 * isEmpty()         - Checks whether the linked list is empty, O(1).
 *
 * add(T element)    - Adds an element to the tail of the linked list, O(1).
 *
 * addFirst(T element)
 *                   - Adds an element to the head of the linked list, O(1).
 *
 * addAt(int index, T element)
 *                   - Inserts an element at the specified index, throws an
 *                     exception if the index is out of bounds, O(n).
 *
 * peekFirst()       - Returns the data of the head node without removing it,
 *                     throws an exception if the list is empty, O(1).
 *
 * peekLast()        - Returns the data of the tail node without removing it,
 *                     throws an exception if the list is empty, O(1).
 *
 * removeFirst()     - Removes and returns the first node in the list, O(1).
 *
 * removeLast()      - Removes and returns the last node in the list, O(1).
 *
 * removeAt(int index)
 *                   - Removes the node at the specified index, throws an
 *                     exception if the index is invalid, O(n).
 *
 * remove(Object obj)
 *                   - Removes the first occurrence of the specified element
 *                     from the list, O(n).
 *
 * indexOf(Object obj)
 *                   - Returns the index of the first occurrence of the specified
 *                     element, or -1 if the element is not found, O(n).
 *
 * contains(Object obj)
 *                   - Returns true if the list contains the specified element,
 *                     otherwise false, O(n).
 *
 * iterator()        - Returns an iterator to traverse the list in a forward
 *                     direction.
 *
 * toString()        - Returns a string representation of the linked list in a
 *                     human-readable format.
 *
 * Node<T> constructor
 *                   - Constructs a new node with the given data, previous node,
 *                     and next node references.
 *
 * main()            - Example usage of the doubly linked list operations with
 *                     test cases for adding, removing, and searching elements.
 */

package com.zilaidawwab.algorithms.datastructures.linkedlist;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    // Internal Node class to represent data
    private static class Node<T> {
        private T data;
        private Node<T> prev, next;
        
        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // Empty the linked list O(n)
    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    // Return the size of the linked list
    public int size() {
        return size;
    }

    // Is the linked list empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Add an element to the tail of the linked list, O(1)
    public void add(T element) {
        addLast(element);
    }

    private void addLast(T element) {
        if(isEmpty()) head = tail = new Node<T>(element, null, null);
        else {
            tail.next = new Node<T>(element, tail, null);
            tail = tail.next;
        }
        size++;
    }

    // Add an element to the beginning of the linked list, O(1)
    public void addFirst(T element) {
        if(isEmpty()) head = tail = new Node<T>(element, null, null);
        else {
            head.prev = new Node<T>(element, null, head);
            head = head.prev;
        }
        size++;
    }

    // Add an element at a specified index
    public void addAt(int index, T data) throws Exception {
        if(index < 0 || index > size) throw new Exception("Illegal Index");

        if(index == 0) {
            addFirst(data);
            return;
        }

        if(index == size) {
            addLast(data);
            return;
        }

        Node<T> temp = head;
        for(int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        Node<T> newNode = new Node<>(data, temp, temp.next);
        temp.next.prev = newNode;
        temp.next = newNode;
        size++;
    }

    // Check the value of the first node if it exists, O(1)
    public T peekFirst() {
        if(isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    // Check the value of the last node if it exists, O(1)
    public T peekLast() {
        if(isEmpty()) throw new RuntimeException("Empty list");
        return tail.data;
    }

    // Remove the first value at the head of the linked list, O(1)
    public T removeFirst() {
        if(isEmpty()) throw new RuntimeException("Empty list");

        // Extract the data from the head and move the head pointer forward one node
        T data = head.data;
        head = head.next;
        --size;

        // If the list became empty, set the tail to null
        if(isEmpty()) tail = null;

        // Do a memory cleanup of the previous node
        else head.prev = null;

        // Return the data of the node that is removed
        return data;
    }

    // Remove the value from the tail of the linked list, O(1)
    public T removeLast() {
        if(isEmpty()) throw new RuntimeException("Empty list");

        // Extract the data from the tail and move the tail pointer one node backward
        T data = tail.data;
        tail = tail.prev;
        --size;

        // If the list became empty, set the head to null
        if(isEmpty()) head = null;

        // Do a memory cleanup for the node just removed
        else tail.next = null;

        // Returning the data of the removed node
        return data;
    }

    // Remove an arbitrary node from the linked list, O(1)
    private T remove(Node<T> node) {
        // If the node to be removed is head or tail, we'll handle that independently
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();

        // Made the pointers of adjacent nodes skip over 'node'
        node.next.prev = node.prev;
        node.prev.next = node.next;

        // Temporarily store the data we want to return
        T data = node.data;

        // Memory cleanup
        node.data = null;
        node = node.prev = node.next = null;
        --size;

        return data;
    }

    // Remove the node at a particular index, O(n)
    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();

        int i;
        Node<T> trav;

        // Search from the front of the list
        if(index < size / 2) {
            trav = head;
            for(i = 0; i != index; i++) {
                trav = trav.next;
            }
            // Search from the back of the list
        } else {
            trav = tail;
            for(i = size - 1; i != index; i--) {
                trav = trav.prev;
            }
        }
        return remove(trav);
    }

    // Remove a particular value in the linked list, O(n)
    public boolean remove(Object obj) {
        Node<T> trav = head;

        // Support searching for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if(trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
            // Search for non-null object
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    // Find the index of a particular value in the linked list, O(n)
    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;

        // Support searching for null
        if(obj == null) {
            for (; trav != null; trav = trav.next, index++) {
                if(trav.data == null) return index;
            }
            // Search for non-null object
        } else {
            for(; trav != null; trav = trav.next, index++) {
                if(obj.equals(trav.data)) return index;
            }
        }
        return -1;
    }

    // Check if a value is contained in the linked list, O(n)
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.data);
            if (trav.next != null) sb.append(", ");
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();

        // Test: Add elements
        dll.add(1);
        dll.add(2);
        dll.add(3);
        dll.add(4);

        System.out.println("List after adding 1, 2, 3, 4: " + dll);  // Expected: [1, 2, 3, 4]

        // Test: AddFirst
        dll.addFirst(0);
        System.out.println("List after adding 0 at the start: " + dll);  // Expected: [0, 1, 2, 3, 4]

        // Test: AddLast
        dll.addLast(5);
        System.out.println("List after adding 5 at the end: " + dll);  // Expected: [0, 1, 2, 3, 4, 5]

        // Test: AddAt
        try {
            dll.addAt(3, 10);  // Add 10 at index 3
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("List after adding 10 at index 3: " + dll);  // Expected: [0, 1, 2, 10, 3, 4, 5]

        // Test: RemoveFirst
        dll.removeFirst();
        System.out.println("List after removing the first element: " + dll);  // Expected: [1, 2, 10, 3, 4, 5]

        // Test: RemoveLast
        dll.removeLast();
        System.out.println("List after removing the last element: " + dll);  // Expected: [1, 2, 10, 3, 4]

        // Test: RemoveAt
        dll.removeAt(2);  // Remove element at index 2 (value 10)
        System.out.println("List after removing the element at index 2: " + dll);  // Expected: [1, 2, 3, 4]

        // Test: Contains
        System.out.println("Contains 3: " + dll.contains(3));  // Expected: true
        System.out.println("Contains 10: " + dll.contains(10));  // Expected: false

        // Test: IndexOf
        System.out.println("Index of 3: " + dll.indexOf(3));  // Expected: 2
        System.out.println("Index of 10: " + dll.indexOf(10));  // Expected: -1
    }
}
