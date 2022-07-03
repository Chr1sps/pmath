package PMath;

import PMath.shapes.Point;
import PMath.shapes.Segment;

public class utils {

    private static double getPolar(Point point) {
        Point zero = new Point(0, 0), plus = new Point(0, 1), minus = new Point(0, -1);
        if (point.getY() < 0)
            return 2 + determinant(point, zero, minus);
        else
            return determinant(point, zero, plus);
    }

    /**
     * Calculates the determinant of the matrix.
     * Equivalent to:
     * 
     * <pre>
     * |a.x, b.x, c.x|
     *|a.y, b.y, c.y|
     *|  1,   1,   1|
     * </pre>
     * 
     * @param a
     * @param b
     * @param c
     * @return double
     */
    public static double determinant(Point a, Point b, Point c) {
        return ((a.getX() - c.getX()) * (b.getY() - a.getY())) - ((a.getX() - b.getX()) * (c.getY() - a.getY()));
    }

    /**
     * Calculates the determinant of the matrix
     * 
     * <pre>
     * |p.x, s.a.x, s.b.x|
     *|p.y, s.a.y, s.b.y|
     *|  1,     1,     1|
     * </pre>
     * 
     * @param p
     * @param s
     * @return double
     */
    public static double determinant(Point p, Segment s) {
        return determinant(p, s.getA(), s.getB());
    }

    /**
     * Returns true if first {@link Point} is lexicografically smaller than the
     * other
     * (checks X coordinate first, then Y).
     * 
     * @param first
     * @param other
     * @return boolean
     */
    public static boolean lessThanLexicalX(Point first, Point other) {
        return (first.getX() < other.getX()) || (first.getX() == other.getX() && first.getY() < other.getY());
    }

    /**
     * Returns true if first {@link Point} is lexicografically smaller than the
     * other
     * (checks Y coordinate first, then X).
     * 
     * @param first
     * @param other
     * @return boolean
     */
    public static boolean lessThanLexicalY(Point first, Point other) {
        return (first.getY() < other.getY()) || (first.getY() == other.getY() && first.getX() < other.getX());
    }

    /**
     * Returns true if first {@link Point} has a smaller polar angle (when looking
     * at X axis) than the other
     * 
     * @param first
     * @param other
     * @return boolean
     */
    public static boolean lessThanPolar(Point first, Point other) {
        return getPolar(first) < getPolar(other);
    }
}
