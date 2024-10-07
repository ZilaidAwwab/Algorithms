/*
 * Minimum-weight feedback edge set.
 * A feedback edge set of a graph is a subset of edges that contains at least one 
 * edge from every cycle in the graph. If the edges of a feedback edge set are 
 * removed, the resulting graph is acyclic. Given an edge-weighted graph, design an 
 * efficient algorithm to find a feedback edge set of minimum weight. Assume the 
 * edge weights are positive.
 */

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class EdgeTest {
    public Queue<Edge> MFES(EdgeWeightedGraph g) {
        MaxPQ<Edge> pq = new MaxPQ<>();
        Queue<Edge> mfes = new Queue<>();

        for(Edge e: g.edges()) {
            pq.insert(e);
        }

        UF uf = new UF(g.V());
        while (!pq.isEmpty()) {
            Edge e = pq.delMax();
            int v = e.either();
            int w = e.other(v);

            if(!uf.connected(v, w)) {
                uf.union(v, w);
            } else {
                mfes.enqueue(e);
            }
        }
        return mfes;
    }
}
