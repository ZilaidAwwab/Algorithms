/*
 * UnionFind API
 *
 * Implements a Union-Find data structure (also known as Disjoint Set Union) that supports
 * efficient merging and querying of sets, leveraging path compression and union by size.
 * It provides a way to maintain disjoint sets and enables dynamic connectivity among elements.
 *
 * find(int p)               - Returns the root identifier of the component containing 'p'; applies path compression for efficiency.
 * connected(int p, int q)   - Checks if elements 'p' and 'q' belong to the same component.
 * componentSize(int p)      - Returns the size of the component containing element 'p'.
 * size()                    - Returns the total number of elements in the Union-Find structure.
 * components()              - Returns the number of disjoint components or sets.
 * unify(int p, int q)       - Merges the components containing elements 'p' and 'q' if they are not already connected.
 */

package com.zilaidawwab.algorithms.datastructures.unionfind;

public class UnionFind {
    
    // The number of elements in the union find
    private int size;

    // Used to track the size of each of the component
    private int[] sz;

    // id[i] points to the parent of i, if id[i] = i then i is the root node
    private int[] id;

    // Tracks the number of component in the union find
    private int numComponents;

    public UnionFind(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size <= 0 is not allowed");

        this.size = numComponents = size;
        sz = new int[size];
        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i; // Link to itself initially (self root)
            sz[i] = 1; // Each component is initially of size 1
        }
    }

    // Find which component / set 'p' belongs to, take amortized constant time
    public int find(int p) {
        // Find the root of the component / set
        int root = p;
        while (root != id[root]) root = id[root];

        // Compress the path leading back to the root
        // Doing this operation is called "path compression" and this is 
        // what gives us amortized time complexity
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;
    }

    // Another method to do the find operation (using recursive formulation)
    // public int find(int p) {
    //     if (p == id[p]) return p;
    //     return id[p] = find(id[p]);
    // }

    // Return whether the elements 'p' and 'q' are in the same component / set
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // Return the size of the component / set 'p' belongs to
    public int componentSize(int p) {
        return sz[find(p)];
    }

    // Return the number of elements in the union find / disjoint set
    public int size() {
        return size;
    }

    // Returns the number of remaining components / sets
    public int components() {
        return numComponents;
    }

    // Unify merges the sets containing elements 'p' and 'q'
    public void unify(int p, int q) {
        // These elements are already in the same group
        if (connected(p, q)) return;

        int root1 = find(p);
        int root2 = find(q);

        // Merge smaller components / sets into larger one
        if (sz[root1] < sz[root2]) {
            sz[root2] += sz[root1];
            id[root1] = root2;
            sz[root1] = 0;
        } else {
            sz[root1] += sz[root2];
            id[root2] = root1;
            sz[root2] = 0;
        }

        // Since the roots found are different we know that the number of components has decreased by one
        numComponents--;
    }
}
