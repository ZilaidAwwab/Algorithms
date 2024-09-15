import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
    private final SET<Point2D> pointSet;

    // Constructor to create an empty set of points
    public PointSET() {
        pointSet = new SET<>();
    }

    // Check if the set is empty
    public boolean isEmpty() {
        return pointSet.isEmpty();
    }

    // Get the number of points in the set
    public int size() {
        return pointSet.size();
    }

    // Add a point to the set if it is not already present
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        pointSet.add(p);
    }

    // Check if the set contains the given point
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        return pointSet.contains(p);
    }

    // Draw all points in the set
    public void draw() {
        for (Point2D point : pointSet) {
            StdDraw.point(point.x(), point.y());
        }
    }

    // Return all points inside the given rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("Rectangle cannot be null");
        }
        SET<Point2D> pointsInRange = new SET<>();
        for (Point2D point : pointSet) {
            if (rect.contains(point)) {
                pointsInRange.add(point);
            }
        }
        return pointsInRange;
    }

    // Return the nearest point in the set to the given query point
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        if (isEmpty()) {
            return null;
        }
        Point2D nearestPoint = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Point2D point : pointSet) {
            double distance = point.distanceSquaredTo(p);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

    // Main method for testing (optional)
    public static void main(String[] args) {
        // You can add test cases here
    }
}
