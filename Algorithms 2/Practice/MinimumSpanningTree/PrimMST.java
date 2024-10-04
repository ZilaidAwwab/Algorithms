import java.util.ArrayList;

import edu.princeton.cs.algs4.IndexMinPQ;

public class PrimMST {
    private Edge[] edgeTo;      // shortest edge from tree vertex
    private double[] distTo;    // distTo[w] = edgeTo[w].weight()
    private boolean[] marked;   // true if v on tree
    private IndexMinPQ<Double> pq;  // eligible crossing edge

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];

        for(int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        pq = new IndexMinPQ<>(G.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);      // initialize pq with 0, weight 0

        while (!pq.isEmpty()) {
            visit(G, pq.delMin());    // add closest vertex to tree
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // add v to tree, update data structure
        marked[v] = true;
        for(Edge e: G.adj(v)) {
            int w = e.other(v);
            if(!marked[w]) continue;    // v-w is ineligible
            if(e.weight() < distTo[w]) {
                // edge e is new best connection from tree to w
                edgeTo[w] = e;
                distTo[w] = e.weight();

                if(pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        ArrayList<Edge> mstEdges = new ArrayList<>();
        for (int v = 1; v < edgeTo.length; v++) {
            if (edgeTo[v] != null) {
                mstEdges.add(edgeTo[v]);
            }
        }
        return mstEdges;
    }

    public double weight() {
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        return totalWeight;
    }
}
