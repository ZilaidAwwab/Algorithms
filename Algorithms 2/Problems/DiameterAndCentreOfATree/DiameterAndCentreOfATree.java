/*
 * Diameter and center of a tree. Given a connected graph with no cycles
 * Diameter: design a linear-time algorithm to find the longest simple 
 * path in the graph.
 * Center: design a linear-time algorithm to find a vertex such that 
 * its maximum distance from any other vertex is minimized.
 */

public class DiameterAndCentreOfATree {
    public static void diameter(Graph g) {
        assert g.V() > 0;

        // first pass, find all the paths from 0, and find the farest vertice from 0
        int max = farest(g, 0);

        // Second pass, from the farest vetice, find all paths from it
        BreadthFirstPaths path = new BreadthFirstPaths(g, max);
        int end = farest(g, max);

        for(Integer v: path.pathTo(end)) {
            System.out.println(v);
        }
    }

    private static int farest(Graph g, int v) {
        BreadthFirstPaths path = new BreadthFirstPaths(g, v);
        int max = 0;
        int len = 0;

        for(int i = 1; i < g.V(); i++) {
            if(path.distTo(i) > len) {
                max = i;
                len = path.distTo(i);
            }
        }
        return max;
    }

    private static int center(Graph g) {
        assert g.V() > 0;

        // first find the farest as above
        int start = farest(g, 0);
        int end = farest(g, start);
        BreadthFirstPaths spath = new BreadthFirstPaths(g, start);
        BreadthFirstPaths epath = new BreadthFirstPaths(g, end);

        int result = start;
        for(int i = 0; i < g.V(); i++) {
            // this comparison is for the purpose that whereever the start and end aligns, thats the center
            if(spath.distTo(i) == epath.distTo(i) || spath.distTo(i) == epath.distTo(i) + 1) result = i;
        }
        return result;
    }
}
