import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private int segmentsCount;
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
      // finds all line segments containing 4 or more points
        if (points == null) {
          throw new IllegalArgumentException("points can't be null");
        }

        for (Point p: points) {
          if (p == null) {
            throw new IllegalArgumentException("points can't be null");
          }
        }

        this.segmentsCount = 0;

        Point[] p = new Point[points.length];
        System.arraycopy(points, 0, p, 0, points.length);
        this.segments = this.analyze(p);
    }

    private LineSegment[] analyze(Point[] points) {
        LineSegment[] tmpSegment = new LineSegment[points.length*4];
        MergeX.sort(points);

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];

            int copySize = points.length - i - 1;
            Point[] copyPoints = new Point[copySize];
            System.arraycopy(points, i+1, copyPoints, 0, copySize);
            MergeX.sort(copyPoints, p.slopeOrder());

            int lineCounter = 2;
            for (int j = 1; j < copySize; j++) {
              if (Double.compare(p.slopeTo(copyPoints[j-1]), p.slopeTo(copyPoints[j])) == 0) {
                lineCounter++;
              } else {
                if (lineCounter >= 4) {
                  tmpSegment[segmentsCount++] = new LineSegment(p, copyPoints[j-1]);
                }
                lineCounter = 2;
              }
            }
            if (lineCounter >= 4) {
              tmpSegment[segmentsCount++] = new LineSegment(p, copyPoints[copyPoints.length-1]);
            }
        }
        LineSegment[] realSegments = new LineSegment[segmentsCount];
        for (int i = 0; i < segmentsCount; i++) {
            realSegments[i] = tmpSegment[i];
        }
        return realSegments;
    }

    public int numberOfSegments() {
      // the number of line segments
        return segmentsCount;
    }

    public LineSegment[] segments() {
      // the line segments
        LineSegment[] returnSegments = new LineSegment[segments.length];
        System.arraycopy(segments, 0, returnSegments, 0, segments.length);
        return returnSegments;
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p: points) {
            p.draw();
        }
        StdDraw.show();

        // point and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment: collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
