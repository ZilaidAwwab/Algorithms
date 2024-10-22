/*
 * SinglyLinkedList API
 *
 * This class implements a singly linked list with basic operations such as
 * insertion, deletion, searching, and more. It also includes methods for
 * reversing the list, detecting loops, and merging two sorted lists.
 *
 * Node              - Inner class representing a node in the linked list,
 *                     containing data and a reference to the next node.
 *
 * display()         - Prints the linked list in a readable format from the
 *                     head node to the end.
 *
 * length()          - Returns the number of nodes present in the linked list.
 *
 * insertFirst(int value)
 *                   - Inserts a new node with the given value at the head of
 *                     the list.
 *
 * insert(int position, int value)
 *                   - Inserts a new node at the specified position. If the
 *                     position is 1, the node is added at the head.
 *
 * insertLast(int value)
 *                   - Inserts a new node at the end of the list.
 *
 * deleteFirst()     - Removes and returns the first node from the list.
 *
 * delete(int position)
 *                   - Removes the node at the specified position. Assumes the
 *                     position is valid and starts from 1.
 *
 * deleteLast()      - Removes and returns the last node in the list.
 *
 * find(int searchKey)
 *                   - Searches for the node containing the given value and
 *                     returns true if found, else false.
 *
 * reverse()         - Reverses the list in place and returns the new head node.
 *
 * getMiddleNode()   - Returns the middle node of the linked list using the
 *                     two-pointer technique.
 *
 * getNthNodeFromEnd(int n)
 *                   - Returns the nth node from the end of the list. Throws an
 *                     exception if n is larger than the number of nodes.
 *
 * insertInSortedList(int value)
 *                   - Inserts a new node in a sorted list at the appropriate
 *                     position.
 *
 * deleteNode(int key)
 *                   - Removes the node containing the specified key.
 *
 * containsLoop()    - Detects if the linked list contains a loop using the
 *                     fast and slow pointer technique.
 *
 * startNodeInALoop()
 *                   - If a loop is detected, returns the node where the loop
 *                     begins.
 *
 * removeLoop()      - Removes a detected loop in the linked list.
 *
 * createALoopInALinkedList()
 *                   - Creates a loop in the linked list for testing purposes.
 *
 * merge(Node a, Node b)
 *                   - Merges two sorted linked lists into one sorted linked
 *                     list and returns the new head.
 */

package com.zilaidawwab.algorithms.datastructures.linkedlist;

public class SinglyLinkedList {
    
    private Node head;

    public static class Node {
        private final int data; // Can be a generic type
        private Node next; // Reference to the next node in the list

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " --> ");
            current = current.next;
        }

        System.out.print("null");
        System.out.println();
    }

    public int length() {
        if(head == null) return 0;

        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void insertFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    public void insert(int position, int value) {
        // 1 -> 4 -> 5
        // 1 -> 6 -> 4 -> 5
        Node node = new Node(value);

        if(position == 1) {
            node.next = head;
            head = node;
        } else {
            Node previous = head;
            int count = 1; // position - 1

            while(count < position - 1) {
                previous = previous.next;
                count++;
            }

            Node current = previous.next;
            previous.next = node;
            node.next = current;
        }
    }

    public void insertLast(int value) {
        Node newNode = new Node(value);
        if(head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (null != current.next) {
            current = current.next;
        }
        current.next = newNode;
    }

    public Node deleteFirst() {
        if(head == null) {
            return null;
        }
        Node temp = head;
        head = head.next;
        temp.next = null;
        return temp;
    }

    public void delete(int position) {
        // position is valid and starting from 1
        // 3 -> 4 -> 7 -> 8 -> 9 -> null
        if(position == 1) {
            head = head.next;
        } else {
            Node previous = head;
            int count = 1;
            while(count < position - 1) {
                previous = previous.next;
                count++;
            }

            Node current = previous.next;
            previous.next = current.next;
        }
    }

    public Node deleteLast() {
        if(head == null) return null;

        if(head.next == null) {
            Node temp = head;
            head = null;
            return temp;
        }

        Node current = head;
        Node previous = null;

        while(current.next != null) {
            previous = current;
            current = current.next;
        }

        previous.next = null; // breaking the chain
        return current;
    }

    public boolean find(int searchKey) {
        if(head == null) return false;

        Node current = head;
        while (current != null) {
            if(current.data == searchKey) return true;
            current = current.next;
        }
        return false;
    }

    public Node reverse() {
        if(head == null) return null;

        Node current = head;
        Node previous = null;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    public Node getMiddleNode() {
        if(head == null) return null;

        Node slowPtr = head;
        Node fastPtr = head;

        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        return slowPtr;
    }

    public Node getNthNodeFromEnd(int n) {
        if(head == null) return null;

        if(n <= 0) throw new IllegalArgumentException("Invalid value: n = " + n);

        Node mainPtr = head;
        Node refPtr = head;

        int count = 0;

        while (count < n) {
            if(refPtr == null) throw new IllegalArgumentException(n + " is greater than the number of nodes in list");
            refPtr = refPtr.next;
            count++;
        }

        while (refPtr != null) {
            refPtr = refPtr.next;
            mainPtr = mainPtr.next;
        }
        return mainPtr;
    }

    public Node insertInSortedList(int value) {
        Node newNode = new Node(value);
        if(head == null) return newNode;

        Node current = head;
        Node temp = null;

        while (current != null && current.data < newNode.data) {
            temp = current;
            current = current.next;
        }

        newNode.next = current;
        assert temp != null;
        temp.next = newNode;
        return head;
    }

    public void deleteNode(int key) {
        Node current = head;
        Node temp = null;

        if(current != null && current.data == key) {
            head = current.next;
            return;
        }

        while (current != null && current.data != key) {
            temp = current;
            current = current.next;
        }

        if(current == null) return;
        temp.next = current.next;
    }

    public boolean containsLoop() {
        Node fastPtr = head;
        Node slowPtr = head;

        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;

            if(fastPtr == slowPtr) return true;
        }
        return false;
    }

    public Node startNodeInALoop() {
        Node fastPtr = head;
        Node slowPtr = head;

        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;

            if(fastPtr == slowPtr) return getStartingNode(slowPtr);
        }
        return null;
    }

    private Node getStartingNode(Node slowPtr) {
        Node temp = head;
        while (temp != slowPtr) {
            temp = temp.next;
            slowPtr = slowPtr.next;
        }
        return temp; // starting node of the loop
    }

    public void removeLoop() {
        Node fastPtr = head;
        Node slowPtr = head;

        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;

            if(slowPtr == fastPtr) {
                removeLoop(slowPtr);
                return;
            }
        }
    }

    private void removeLoop(Node slowPtr) {
        Node temp = head;
        while (temp.next != slowPtr.next) {
            temp = temp.next;
            slowPtr = slowPtr.next;
        }
        slowPtr.next = null;
    }

    public void createALoopInALinkedList() {
        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);
        Node sixth = new Node(6);

        head = first;
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        sixth.next = third;
    }

    public static Node merge(Node a, Node b) {
        // a => 1-> 3-> 5-> null
        // b => 2-> 4-> 6-> null
        // result=> 1-> 2-> 3-> 4-> 5-> 6 -> null

        Node dummy = new Node(0);
        Node tail = dummy;
        while (a != null && b != null) {
            if(a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        if(a == null) {
            tail.next = b;
        } else {
            tail.next = a;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        SinglyLinkedList sll1 = new SinglyLinkedList();
        sll1.insertLast(1);
        sll1.insertLast(4);
        sll1.insertLast(8);

        SinglyLinkedList sll2 = new SinglyLinkedList();
        sll2.insertLast(4);
        sll2.insertLast(6);
        sll2.insertLast(8);
        sll2.insertLast(12);
        sll2.insertLast(23);

        sll1.display();
        sll2.display();

        SinglyLinkedList result = new SinglyLinkedList();
        result.head = merge(sll1.head, sll2.head);

        result.display();
    }
}
