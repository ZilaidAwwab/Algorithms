/*
Question:
Red–black BST with no extra memory. Describe how to save the memory for 
storing the color information when implementing a red–black BST.

Answer:
Approach to Save Memory: Using "Color" in the Structure of the Tree Itself
The key idea is to eliminate the need to store an explicit color by leveraging 
the tree structure itself to infer the necessary information. One approach is 
to represent red links as left-leaning links in a binary search tree. This is 
sometimes called a Left-Leaning Red-Black Tree (LLRB Tree).

Strategy: Left-Leaning Red-Black Tree (LLRB)
In a standard Red-Black Tree, a red link is a link between a parent and a child 
node that represents a "2-3" node. In a Left-Leaning Red-Black Tree (LLRB):

1. Red links are represented as left child links: We can represent the red links 
implicitly by ensuring that any "red" child link is always a left child of its 
parent node.

2. Black links are normal binary links: All other links in the tree (other than red left links) are black links.

The advantage here is that, by maintaining a specific structure of the tree (always left-leaning for red links), we can avoid storing the color information as an extra bit per node.

Key Properties of Left-Leaning Red-Black Trees
1. Red Nodes Always Lean Left: Every red node (representing a 3-node) is always 
a left child.
2. No Consecutive Red Links: There cannot be two consecutive red links (i.e., 
a parent and child both with left-leaning red links).
3. Balance Maintenance through Rotations: The tree uses left rotations, right 
rotations, and color flips to maintain its balance.

Implementation Strategy
In an LLRB Tree, we represent the tree operations in such a way that they keep 
the tree balanced and maintain the properties mentioned above:

1. Left Rotation: A left rotation is used when a right child is a red link 
(right-leaning red link needs to be corrected to lean left).
2. Right Rotation: A right rotation is applied when there is a left-leaning 
red link and its left child is also a red link (to prevent consecutive red links).
3. Color Flip: A color flip is performed when both children of a node are red 
(converting the node to red and both children to black).
*/

class RedBlackBSTWithNoExtraMemory {
    private Node root;

    class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    public Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

    public Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    public Node flipColors(Node h) {
        // Flip the "colors" by re-structuring the nodes
        // Here, we treat nodes as having flipped colors implicitly
        h.left = rotateLeft(h.left);
        h.right = rotateLeft(h.right);
        return h;
    }

    public Node insert(Node h, int key) {
        if (h == null) return new Node(key);

        if (key < h.key) h.left = insert(h.left, key);
        else if (key > h.key) h.right = insert(h.right, key);
        else return h;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);       // Rotate left to fix right-leaning red link
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);   // Rotate right to prevent consecutive red links
        if (isRed(h.left) && isRed(h.right)) flipColors(h);            // Color flip if both children are red

        return h;
    }

    public boolean isRed(Node x) {
        if (x == null) return false;
        // Assume that if a node is "leaning" left, it represents a red link
        return x.left != null;  // Simplified check for educational purposes
    }

    public void insert(int key) {
        root = insert(root, key);
    }
}
