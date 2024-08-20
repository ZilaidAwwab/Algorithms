import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
      // constructs the point (x, y)
        this.x = x;
        this.y = y;
    }

    public void draw() {
      // draws this point
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
      // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
  
    public String toString() {
      // string representation
        return "(" + x + ", " + y +")";
    } 

    public int compareTo(Point that) {
      // compare two points by y-coordinates, breaking ties by x-coordinates
        if (this.x == that.x && this.y == that.y) return 0;
        else if (this.y > that.y || (this.y == that.y && this.x < that.y)) return -1;
        else return 1;
    }
  
    public double slopeTo(Point that) {
      // the slope between this point and that point
        if (that.y == this.y && that.x == this.x) return Double.NEGATIVE_INFINITY;
        else if (that.x == this.x) return Double.POSITIVE_INFINITY;
        else if (that.y == this.y) return +0.0;
        else return (double) (that.y - this.y) / (double) (that.x - this.x);
    }
  
    public Comparator<Point> slopeOrder() {
      // compare two points by slopes they make with this point
        return new Comparator<Point>() {
          @Override
          public int compare(Point p1, Point p2) {
            if (slopeTo(p1) < slopeTo(p2)) return -1;
            else if (slopeTo(p1) > slopeTo(p2)) return 1;
            else return 0;
          }
        };
    }
    
    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(5, 0);
        assert p1.slopeTo(p2) == 0.0;

        p2 = new Point(-5, 0);
        assert p1.slopeTo(p2) == 0.0;

        p2 = new Point(0, 5);
        assert p1.slopeTo(p2) == Double.POSITIVE_INFINITY;

        p2 = new Point(0, -5);
        assert p1.slopeTo(p2) == Double.POSITIVE_INFINITY;

        p2 = new Point(1, 1);
        assert p1.slopeTo(p2) == 1.0;

        p2 = new Point(-1, -1);
        assert p1.slopeTo(p2) == 1.0;

        p1 = new Point(19000, 10000);
        p2 = new Point(1234, 5678);
        System.out.println(p1.slopeTo(p2));
    }
}
