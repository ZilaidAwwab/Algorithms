/*
Question
Design an algorithm to perform an inorder traversal of a binary search tree using 
only a constant amount of extra space.
*/

public class InorderTraversalWithConstantExtraSpace {
    private class Node {
        private int key;           // sorted by key
        private int val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(int key, int val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public void inOrder(Node root) {
        if (root == null) return;  // If the root is null, there's nothing to traverse
    
        Node previous;  // To keep track of the predecessor
        Node current = root;  // Start from the root
    
        while (current != null) {
            // Case 1: If the current node has no left child
            if (current.left == null) {
                // Print the current node's value
                System.out.println(current.val);
                // Move to the right child
                current = current.right;
            } else {
                // Case 2: The current node has a left child
                // Find the rightmost node in the left subtree (predecessor)
                previous = current.left;
    
                while (previous.right != null && previous.right != current) {
                    previous = previous.right;
                }
    
                // If the rightmost node's right child is null, establish a thread to the current node
                if (previous.right == null) {
                    previous.right = current;  // Thread created
                    current = current.left;  // Move to the left child
                } else {
                    // If the rightmost node's right child is already pointing to the current node, remove the thread
                    previous.right = null;  // Restore the tree structure
                    System.out.println(current.val);  // Print the current node
                    current = current.right;  // Move to the right child
                }
            }
        }
    }    
}
