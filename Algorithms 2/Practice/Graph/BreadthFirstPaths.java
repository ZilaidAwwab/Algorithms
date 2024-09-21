/* Breadth-first search to find paths in a graph */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;;

public class BreadthFirstPaths {
    private boolean[] marked;   // is a shortest path to this vertex known
    private int[] edgeTo;       // last vertex on known path to this vertex
    private final int s;        // source

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;       // mark the source
        queue.enqueue(s);       // and put it(source) on the queue
        while (!queue.isEmpty()) {
            int v = queue.dequeue();    // remove next vertex from the queue
            for (int w: G.adj(v)) {
                if(!marked[w]) {        // for every unmarked adjacent vertex
                    edgeTo[w] = v;      // save last edge on a shortest path
                    marked[w] = true;   // mark it because path is known
                    queue.enqueue(w);   // and add it to the queue
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}

/*
 * This Graph client uses breadth-first search to find paths in a graph with the 
 * fewest number of edges from the source s given in the constructor. The bfs() 
 * method marks all vertices connected to s, so clients can use hasPathTo() to 
 * determine whether a given vertex v is connected to s and pathTo() to get a path 
 * from s to v with the property that no other such path from s to v has fewer edges.
 */
