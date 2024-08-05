import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP = 0;
    // since it is initially closed that's why this is boolean and returns false
    private boolean[][] opened;
    private final int size;
    private final int bottom;
    private int openSites;
    private final WeightedQuickUnionUF qf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
      if (n <= 0) {
        throw new IllegalArgumentException();
      }
      size = n;
      // for a 3 * 3 size matrix the bottom one has to be 10
      bottom = size * size + 1;
      // now weighted quick union is +2 becasue it includes top and bottom as well
      qf = new WeightedQuickUnionUF(size * size + 2);
      opened = new boolean[size][size];
      openSites = 0;
    }

    private void validityCheck(int row, int col) {
      if (row <= 0 || row > size || col <= 0 || col > size) {
        throw new IllegalArgumentException("Row or Column out of bound.");
      }
    }

    private int getQuickFindIndex(int row, int col) {
      return size * (row - 1) + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
      validityCheck(row, col);
      opened[row-1][col-1] = true; // open the box
      ++openSites; // incrementing the number of sites

      // Edge case: If any of the top row boxes are opened => Union(box, top)
      if (row == 1) {
        qf.union(getQuickFindIndex(row, col), TOP);
      }

      // Edge case: If any of the bottom row boxes are opened => Union(box, bottom)
      if (row == size) {
        qf.union(getQuickFindIndex(row, col), bottom);
      }

      // if any of the boxes in between top and bottom are open then check for neighbouring unions
      if (row > 1 && isOpen(row - 1, col)) {
        qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row - 1, col));
      }

      if (row < size && isOpen(row + 1, col)) {
        qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row + 1, col));
      }

      if (col > 1 && isOpen(row, col - 1)) {
        qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col - 1));
      }

      if (col < size && isOpen(row, col + 1)) {
        qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col + 1));
      }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
      validityCheck(row, col);
      return opened[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
      validityCheck(row, col);
      if ((row > 0 && row <= size) && (col > 0 && col <= size)) {
        return qf.find(TOP) == qf.find(getQuickFindIndex(row, col));
      } else throw new IllegalArgumentException();
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
      return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
      // checking whether top is connected to bottom
      return qf.find(TOP) == qf.find(bottom);
    }

    public static void main(String[] args) {
      
    }
}
