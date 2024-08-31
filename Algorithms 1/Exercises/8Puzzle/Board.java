import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;

public class Board {
    private final int[][] blocksCache;
    private int zeroI;
    private int zeroJ;

    private final int dimension;
    private int hamming;
    private int manhattan;

    // create a board from a n-by-n array of tiles, 
    // while tiles[row][col] = tile at row(row, col)
    public Board(int[][] blocks) {
        this(blocks, blocks.length);
    }

    private Board(int[][] blocks, int dimension) {
        if (blocks == null) {
            throw new IllegalArgumentException("Blocks can't be null.");
        }

        this.dimension = dimension;
        this.blocksCache = blocksCopy(blocks);
        calculateDistances(blocks);
    }

    private int[][] blocksCopy(int[][] sourceBlocks) {
        int[][] copy = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            System.arraycopy(sourceBlocks[i], 0, copy[i], 0, dimension);
        }
        return copy;
    }

    private void calculateDistances(int[][] blocks) {
        int manhattanCalc = 0;
        int hammingCalc = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != ((dimension * i) + j + 1)) {
                    hammingCalc++;
                }
                if (blocks[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                }
                if (blocks[i][j] == 0) continue;

                int calcI = (blocks[i][j] - 1) / dimension;
                int calcJ = (blocks[i][j] - 1) % dimension;

                if (i != calcI || j != calcJ) {
                    int distanceI = i - calcI;
                    manhattanCalc += distanceI < 0 ? distanceI * -1 : distanceI;

                    int distanceJ = j - calcJ;
                    manhattanCalc += distanceJ < 0 ? distanceJ * -1 : distanceJ;
                }
            }
        }
        this.manhattan = manhattanCalc;
        this.hamming = hammingCalc;
    }

    // board dimensions n
    public int dimension() {
        return dimension;
    }

    // number of tiles out of place
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board
    public boolean isGoal() {
        return hamming == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y == this) return true;

        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;


        if (that.dimension !=  dimension ||
            that.manhattan != manhattan ||
            that.hamming != hamming) return false;
        
        for (int i = 0; i < blocksCache.length; i++) {
            for (int j = 0; j < blocksCache[i].length; j++) {
                if (blocksCache[i][j] != that.blocksCache[i][i]) return false;
            }
        }
        return true;
    }

    // all neighbouring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        addNeighbor(neighborSwap(zeroI, zeroJ, zeroI - 1, zeroJ), neighbors);
        addNeighbor(neighborSwap(zeroI, zeroJ, zeroI + 1, zeroJ), neighbors);
        addNeighbor(neighborSwap(zeroI, zeroJ, zeroI, zeroJ - 1), neighbors);
        addNeighbor(neighborSwap(zeroI, zeroJ, zeroI, zeroJ + 1), neighbors);
        return neighbors;
    }

    private Board neighborSwap(int formI, int formJ, int toI, int toJ) {
        if (toI < 0 || toI >= dimension || toJ < 0 || toJ >= dimension ||
            formI < 0 || formI >= dimension || formJ < 0 || formJ >= dimension) return null;
        
        int[][] blocks = blocksCopy(this.blocksCache);
        int temp = blocks[formI][formJ];
        blocks[toI][toJ] = temp;
        return new Board(blocks, dimension);
    }

    private void addNeighbor(Board b, List<Board> neighbor) {
        if (b != null) neighbor.add(b);
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int swapToI;
        if (zeroI == 0) {
            swapToI = zeroI + 1;
        } else {
            swapToI = zeroI - 1;
        }

        int swapToJ;
        if (zeroJ == 0) {
            swapToJ = zeroJ + 1;
        } else {
            swapToJ = zeroJ - 1;
        }
        return neighborSwap(swapToI, zeroJ, swapToI, swapToJ);
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dimension + "\n");
        for (int i = 0; i < blocksCache.length; i++) {
            for (int j = 0; j < blocksCache[i].length; j++) {
                sb.append(String.format(" %2d", blocksCache[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // unit testing
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }

        Board initial = new Board(blocks);
        blocks[n-1][n-1] = 3;
        Board intial2 = new Board(blocks);

        printBoard(initial);
        System.out.println(initial.equals(intial2));

        initial.neighbors().forEach(b -> printBoard(b));
    }

    private static void printBoard(Board b) {
        System.out.println("==========");
        System.out.println(b);
        System.out.println("dimension: " + b.dimension);
        System.out.println("hamming: " + b.hamming);
        System.out.println("manhatton: " + b.manhattan);
        System.out.println("goal: " + b.isGoal());
        System.out.println("==========");
    }
}
