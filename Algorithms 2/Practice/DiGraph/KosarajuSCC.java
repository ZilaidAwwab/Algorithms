/* Kosaraju’s algorithm for computing strong components */

import edu.princeton.cs.algs4.Digraph;

public class KosarajuSCC {
    private boolean[] marked;   // reached vertices
    private int[] id;           // component identifier
    private int count;          // number of strong components

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for(int s: order.reversePost()) {
            if(!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for(int w: G.adj(v)) {
            if(!marked[w]) dfs(G, w);
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }
}

/*
 * To find strong components, it does a depth-first search in the reverse digraph to 
 * produce a vertex order (reverse postorder of that search) for use in a 
 * depth-first search of the given digraph. 
 */
