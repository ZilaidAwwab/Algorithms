import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private static final double CONFIDENCE = 1.96;
    private final int experimentsCounts;
    private final double[] fractions;

    public PercolationStats(int n, int t) {
      if (n <= 0 || t <= 0) {
        throw new IllegalArgumentException();
      }
      experimentsCounts = t;
      fractions = new double[experimentsCounts];

      for (int expNum = 0; expNum < experimentsCounts; expNum++) {
        Percolation pr = new Percolation(n);
        int openSites = 0;

        while (!pr.percolates()) {
          int i = StdRandom.uniformInt(1, n + 1);
          int j = StdRandom.uniformInt(1, n + 1);

          if (!pr.isOpen(i, j)) {
            pr.open(i, j);
            openSites++;
          }
        }
        double fraction = (double) openSites / (n + n);
        fractions[expNum] = fraction;
      }
    }

    public double mean() {
      return StdStats.mean(fractions);
    }

    public double stddev() {
      return StdStats.stddev(fractions);
    }

    public double confidenceLo() {
      return mean() - ((CONFIDENCE * stddev()) / Math.sqrt(experimentsCounts));
    }
    
    public double confidenceHi() {
      return mean() + ((CONFIDENCE * stddev()) / Math.sqrt(experimentsCounts));
    }

    public static void main(String[] args) {
      int n = Integer.parseInt(args[0]);
      int t = Integer.parseInt(args[1]);
      PercolationStats ps = new PercolationStats(n, t);

      String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
      StdOut.println("mean                    = " + ps.mean());
      StdOut.println("stddev                  = " + ps.stddev());
      StdOut.println("95% confidence interval = " + confidence);
    }
}
