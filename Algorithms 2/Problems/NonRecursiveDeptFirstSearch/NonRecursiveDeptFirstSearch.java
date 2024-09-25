/*
 * Nonrecursive depth-first search
 * Implement depth-first search in an undirected graph without using recursion.
 */

import java.util.Iterator;
import java.util.Stack;

public class NonRecursiveDeptFirstSearch {
    private boolean[] marked;

    private Iterator<Integer> adj(int v) {
        return null;
    }

    public NonRecursiveDeptFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        Stack<Integer> visited = new Stack<Integer>();
        visited.push(v);

        while (!visited.isEmpty()) {
            int temp = visited.peek();
            if(adj(temp).hasNext()) {
                int w = adj(temp).next();
                if(!marked[w]) {
                    marked[w] = true;
                    visited.push(w);
                }
            } else {
                visited.pop();
            }
        }
    }
}
