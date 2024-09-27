/*
 * Hamiltonian path in a DAG. 
 * Given a directed acyclic graph, design a linear-time algorithm to determine 
 * whether it has a Hamiltonian path (a simple path that visits every vertex), and 
 * if so, find one.
 */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

public class HamiltonianPathInADAG {
    public Iterable<Integer> hamiltonianPath(Digraph g) {
        // assert g is a DAG
        Digraph gr = g.reverse();
        int v = findEnd(gr, 0);

        if(v > 0) {
            int count = 1;
            Queue<Integer> path = new Queue<>();
            while (g.outdegree(v) == 1) {
                path.enqueue(v);

                for(int i: g.adj(v)) v = i;
                count++;
            }
            if(count == g.V()) return path;
        }
        return null;
    }

    private int findEnd(Digraph g, int v) {
        if(g.outdegree(v) == 0) return v;
        if(g.outdegree(v) == 1) {
            for(int i: g.adj(v)) return findEnd(g, i);
        }
        return -1;
    }
}
