/*
Question
Check if a binary tree is a BST. Given a binary tree where each Node contains a key, 
determine whether it is a binary search tree. Use extra space proportional to the 
height of the tree.
*/

public class IsBinaryTreeABST {
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

    public boolean checkBST(Node p, int min, int max) {
        if (p == null) return true;
        if(p.key >= max || p.key <= min ) return false;
        return checkBST(p.left, min, p.key) && checkBST(p.right, p.key, max);
    }
}
