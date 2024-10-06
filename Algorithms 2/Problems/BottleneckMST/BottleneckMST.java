/*
 * Bottleneck minimum spanning tree.
 * Given a connected edge-weighted graph, design an efficient algorithm to find a 
 * minimum bottleneck spanning tree. The bottleneck capacity of a spanning tree is 
 * the weights of its largest edge. A minimum bottleneck spanning tree is a spanning 
 * tree of minimum bottleneck capacity.
 */

import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.UF;

public class BottleneckMST {
    public static double findMBST(int n, List<Edge> edges) {
        Collections.sort(edges);

        UF uf = new UF(n);
        double maxEdgeInMST = Double.NEGATIVE_INFINITY;

        // Iterating through the sorted edges and building the MST
        for(Edge edge: edges) {
            int rootU = uf.find(edge.either());
            int rootV = uf.find(edge.other(rootU));

            // if u and v are in different sets add the edge to the MST
            if(rootU != rootV) {
                uf.union(edge.either(), edge.other(rootU));
                maxEdgeInMST = Math.max(maxEdgeInMST, edge.weight());
            }
        }
        return maxEdgeInMST;
    }
}
