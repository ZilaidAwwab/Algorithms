/*
 * BinarySearchTree API
 *
 * This file contains an implementation of a Binary Search Tree (BST) Any comparable data is allowed
 * within this tree (numbers, strings, comparable Objects, etc...). Supported operations include
 * adding, removing, height, and containment checks. Furthermore, multiple tree traversal Iterators
 * are provided including: 1) Pre-order traversal 2) In-order traversal 3) Post-order traversal 4)
 * Level-order traversal
 *
 * isEmpty()           - Returns true if the binary search tree is empty, otherwise false.
 * size()              - Returns the number of nodes in the binary search tree.
 * add(T element)     - Adds the given element to the binary search tree. Returns true if insertion is successful.
 * remove(T element)  - Removes the specified element from the tree if it exists, returns true if successful.
 * contains(T element)- Checks whether the tree contains the specified element.
 * height()            - Computes and returns the height of the tree.
 * traverse(TreeTraversalOrder order) - Returns an iterator for tree traversal based on the specified order: PRE_ORDER, IN_ORDER, POST_ORDER, or LEVEL_ORDER.
 *
 * Private helper methods:
 * add(Node node, T element)       - Recursively adds an element to the tree starting from a specific node.
 * remove(Node node, T element)    - Recursively removes an element starting from a specific node.
 * findMin(Node node)              - Finds and returns the node with the smallest value starting from a given node.
 * findMax(Node node)              - Finds and returns the node with the largest value starting from a given node.
 * contains(Node node, T element)  - Recursively checks if an element is present in the tree starting from a specific node.
 * height(Node node)               - Recursively calculates the height of the tree starting from a specific node.
 * preOrderTraversal()             - Returns an iterator for pre-order tree traversal (Root -> Left -> Right).
 * inOrderTraversal()              - Returns an iterator for in-order tree traversal (Left -> Root -> Right).
 * postOrderTraversal()            - Returns an iterator for post-order tree traversal (Left -> Right -> Root).
 * levelOrderTraversal()           - Returns an iterator for level-order tree traversal (Layer by Layer).
 */

package com.zilaidawwab.algorithms.datastructures.binarysearchtree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>>  {

    // Track the number of nodes in the BST
    private int nodeCount = 0;

    // This BST is a rooted tree, so we maintain a handle on the root node
    private Node root = null;

    // Internal node containing node references and the actual node data
    private class Node {
        T data;
        Node left, right;

        public Node(Node left, Node right, T element) {
            this.data = element;
            this.left = left;
            this.right = right;
        }
    }

    // check if the binary tree is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // get the number of nodes in the tree
    public int size() {
        return nodeCount;
    }

    // Add an element to this binary tree. Returns true if we successfully perform an insertion
    public boolean add(T element) {
        // check if the value already exist in the tree, if it does, don't add it, otherwise do.
        if (contains(element)) return false;
        else {
            root = add(root, element);
            nodeCount++;
            return true;
        }
    }

    // private method to recursively add a value in the binary tree
    private Node add(Node node, T element) {
        // base case: found a leaf node
        if (node == null) node = new Node(null, null, element);
        else {
            // pick a subtree to insert element
            if (element.compareTo(node.data) < 0) node.left = add(node.left, element);
            else node.right = add(node.right, element);
        }
        return node;
    }

    // remove a value from the binary tree
    public boolean remove(T element) {
        // check whether the node that we want to remove exists in the tree first
        if (contains(element)) {
            root = remove(root, element);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T element) {
        if (node == null) return null;
        int cmp = element.compareTo(node.data);
        // dig into left subtree, if the value we are looking for is smaller than the current value, right otherwise
        if (cmp > 0) node.left = remove(node.left, element);
        else if (cmp < 0) node.right = remove(node.right, element);
        // found the node we wish to remove
        else {
            // this is the case with only a right, or no subtree at all. In this situation just swap the nodes we wish
            // to remove with its right child
            if (node.left == null) return node.right; // this is the point where the node is removed (by making it null)
            // this is the case with only a left, or no subtree at all. In this situation just swap the nodes we wish
            // to remove with its left child
            else if (node.right == null) return node.left; // this is also node removal (by making it null)
            // when removing a node from a binary tree with two links the successor of the node being removed can
            // either be the largest value in the left subtree, or the smallest value in the right subtree. Here we
            // are going with the smallest value in the right subtree to swap with the value that is to be removed.
            // the smallest value in the right subtree can be found by traversing far left in the right subtree
            else {
                // find the leftmost node in the right subtree
                Node tmp = findMin(node.right);
                // swap the data
                node.data = tmp.data;
                // go into the right subtree and remove the leftmost node we found and swapped data with. This
                // prevents us from having two nodes in our tree with the same value
                node.right = remove(node.right, tmp.data);
                // if instead we wanted to find the largest node in the left subtree as opposed to the smallest node
                // in the right subtree, we would do this
                // Node tmp = findMax(node.left);
                // node.data = tmp.data;
                // node.left = remove(node.left, tmp.data);
            }
        }
        return node;
    }

    // helper method to find the leftmost node (which has the smallest value)
    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // helper method to find the rightmost node (which has the largest value)
    private Node findMax(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }

    // returns true is the element exist in the tree
    public boolean contains(T element) {
        return contains(root, element);
    }

    // private recursive method to find the element in the tree
    private boolean contains(Node node, T element) {
        // base case: reached bottom, value not found
        if (node == null) return false;
        int cmp = element.compareTo(node.data);

        // dig into the left subtree because the value we are looking for is smaller than the current value
        if (cmp < 0) return contains(node.left, element);
        // dig into the right subtree because the value we are looking for is larger than the current value
        else if (cmp > 0) return contains(node.right, element);
        // we found the value we are looking for
        else return true;
    }

    // computes the height of the tree, 0(n)
    public int height() {
        return height(root);
    }

    // recursive helper method to compute the height of the tree
    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // this method returns an iterator for a given TreeTraversalOrder. The ways in which we can traverse the tree are,
    // pre-order, post-order, in-order, level-order
    public Iterator<T> traverse(TreeTraversalOrder order) {
        return switch (order) {
            case PRE_ORDER -> preOrderTraversal();
            case IN_ORDER -> inOrderTraversal();
            case POST_ORDER -> postOrderTraversal();
            case LEVEL_ORDER -> levelOrderTraversal();
            default -> null;
        };
    }

    // returns an iterator to traverse the tree in pre-order (Root -> Left -> Right)
    private Iterator<T> preOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node> stack = new Stack<>();
        stack.push(root);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node node = stack.pop();
                if (node.right != null) stack.push(node.right);
                // Here we are adding left after right, because pop method remove the last element from stack
                if (node.left != null) stack.push(node.left);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // returns an iterator to traverse the tree in in-order (Left -> Root -> Right)
    private Iterator<T> inOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {
            Node trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                // dig left (this adds the node to the stack from top left to bottom left)
                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }
                // pop the nodes (first the bottom ones come out then check if they have a right node)
                Node node = stack.pop();
                // try moving down right once
                if (node.right != null) {
                    stack.push(node.right);
                    trav = trav.right;
                }
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // returns an iterator to traverse the tree in post-order (Left -> Right -> Root)
    private Iterator<T> postOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node> stack1 = new Stack<>();
        final Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                // since we want left, right, then root so we push root, right then left, so when popped we get
                // what we want (as pop returns elements from the end)
                if (node.left != null) stack1.push(node.left);
                if (node.right != null) stack2.push(node.right);
            }
        }

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack2.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return stack2.pop().data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // returns an iterator to traverse the tree in level-order (Layer by Layer)
    private Iterator<T> levelOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return root != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node node = queue.poll(); // this remove the element from the front (unlike poll in stack)
                assert node != null;
                // Here we are adding up the nodes left then right
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
