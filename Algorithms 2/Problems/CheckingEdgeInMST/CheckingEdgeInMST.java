/*
 * Is an edge in a MST.
 * Given an edge-weighted graph G and an edge e, design a linear-time algorithm to 
 * determine whether e appears in some MST of G.

 * Note: Since your algorithm must take linear time in the worst case, you cannot 
 * afford to compute the MST itself.
 */

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.SET;

public class CheckingEdgeInMST {
    public boolean edgeInMST(EdgeWeightedGraph g, Edge e) {
        SET<Integer> vertices = new SET<>();
        double weight = e.weight();

        for(Edge edge: g.edges()) {
            if(edge.weight() < weight) {
                int v = e.either();
                int w = e.other(v);
                vertices.add(v);
                vertices.add(w);
            }
        }

        int v = e.either();
        int w = e.other(v);
        if(vertices.contains(v) && vertices.contains(w)) return false;

        return true;
    }
}
