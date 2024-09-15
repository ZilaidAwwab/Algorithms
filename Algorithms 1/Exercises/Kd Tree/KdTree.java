import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private static class Node {
        private Point2D point;
        private RectHV rect;
        private Node left, right;
        private boolean vertical;

        public Node(Point2D p, RectHV r, boolean vertical) {
            this.point = p;
            this.rect = r;
            this.vertical = vertical;
        }
    }

    private Node root;
    private int size;

    // Constructor to initialize an empty tree
    public KdTree() {
        this.root = null;
        this.size = 0;
    }

    // Check if the tree is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the number of points in the tree
    public int size() {
        return size;
    }

    // Insert a point into the 2D-tree
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        root = insert(root, p, new RectHV(0, 0, 1, 1), true);
    }

    private Node insert(Node node, Point2D p, RectHV rect, boolean vertical) {
        if (node == null) {
            size++;
            return new Node(p, rect, vertical);
        }

        if (node.point.equals(p)) {
            return node;
        }

        if (vertical) {
            if (p.x() < node.point.x()) {
                RectHV leftRect = new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
                node.left = insert(node.left, p, leftRect, !vertical);
            } else {
                RectHV rightRect = new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
                node.right = insert(node.right, p, rightRect, !vertical);
            }
        } else {
            if (p.y() < node.point.y()) {
                RectHV lowerRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
                node.left = insert(node.left, p, lowerRect, !vertical);
            } else {
                RectHV upperRect = new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
                node.right = insert(node.right, p, upperRect, !vertical);
            }
        }
        return node;
    }

    // Check if the tree contains a given point
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        return contains(root, p);
    }

    private boolean contains(Node node, Point2D p) {
        if (node == null) {
            return false;
        }

        if (node.point.equals(p)) {
            return true;
        }

        if (node.vertical) {
            if (p.x() < node.point.x()) {
                return contains(node.left, p);
            } else {
                return contains(node.right, p);
            }
        } else {
            if (p.y() < node.point.y()) {
                return contains(node.left, p);
            } else {
                return contains(node.right, p);
            }
        }
    }

    // Draw all points in the 2D-tree
    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) {
            return;
        }

        // Draw the point
        StdDraw.point(node.point.x(), node.point.y());

        // Draw the dividing line (red for vertical, blue for horizontal)
        if (node.vertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
        }

        draw(node.left);
        draw(node.right);
    }

    // Return all points inside the given rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("Rectangle cannot be null");
        }
        Queue<Point2D> rangePoints = new Queue<>();
        range(root, rect, rangePoints);
        return rangePoints;
    }

    private void range(Node node, RectHV rect, Queue<Point2D> rangePoints) {
        if (node == null) {
            return;
        }

        if (rect.contains(node.point)) {
            rangePoints.enqueue(node.point);
        }

        if (node.left != null && rect.intersects(node.left.rect)) {
            range(node.left, rect, rangePoints);
        }

        if (node.right != null && rect.intersects(node.right.rect)) {
            range(node.right, rect, rangePoints);
        }
    }

    // Return the nearest point in the 2D-tree to the given query point
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        if (isEmpty()) {
            return null;
        }
        return nearest(root, p, root.point);
    }

    private Point2D nearest(Node node, Point2D p, Point2D nearestPoint) {
        if (node == null) {
            return nearestPoint;
        }

        if (node.point.distanceSquaredTo(p) < nearestPoint.distanceSquaredTo(p)) {
            nearestPoint = node.point;
        }

        if (node.left != null && node.left.rect.distanceSquaredTo(p) < nearestPoint.distanceSquaredTo(p)) {
            nearestPoint = nearest(node.left, p, nearestPoint);
        }

        if (node.right != null && node.right.rect.distanceSquaredTo(p) < nearestPoint.distanceSquaredTo(p)) {
            nearestPoint = nearest(node.right, p, nearestPoint);
        }

        return nearestPoint;
    }

    // Main method for testing (optional)
    public static void main(String[] args) {
        // Add test cases here
    }
}
