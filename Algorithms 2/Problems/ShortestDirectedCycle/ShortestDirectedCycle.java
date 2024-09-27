/* 
 * Shortest directed cycle
 * Given a digraph G, design an efficient algorithm to find a directed cycle with 
 * the minimum number of edges (or report that the graph is acyclic). The running 
 * time of your algorithm should be at most proportional to V(E+V) and use space 
 * proportional to E+V, where V is the number of vertices and E is the number of 
 * edges.
 */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

public class ShortestDirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;
    Stack<Integer> cycle;

    public ShortestDirectedCycle(Digraph g) {
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];

        for(int v = 0; v < g.V(); v++) {
            if(!marked[v]) dfs(g, v);
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        onStack[v] = true;

        for(int i: g.adj(v)) {
            if(!marked[i]) {
                edgeTo[i] = v;
                dfs(g, i);
            } else if(onStack[i]) {
                cycle = new Stack<>();
                for(int x = v; x != i; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(i);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public boolean hasCycle() {
        return cycle != null;
    }
}
