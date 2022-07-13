package PMath.utils;

import PMath.exceptions.IdenticalVerticesException;
import PMath.exceptions.OriginPointException;
import PMath.shapes.Point;
import PMath.shapes.Segment;

public class algorithms {

    /**
     * Returns a value from range <0, 3) that corresponds to the {@link Point}'s
     * polar angle. Throws an {@link OriginPointException} if {@link Point} (0,
     * 0) is passed as an argument.
     * 
     * @param point
     * @return double
     * @throws OriginPointException
     */
    private static double getPolar(Point point) throws OriginPointException {
        Point zero = new Point(0, 0);
        double side;
        if (point.getY() < 0)
            side = -1;
        else
            side = 1;
        Point unit = new Point(0, side);

        try {
            return 2 - side + determinant(point, zero, unit) / new Segment(zero, point).getLength();
        } catch (IdenticalVerticesException e) {
            throw new OriginPointException(
                    "Argument point cannot be the coordinate system origin (point [0, 0]).");
        }
    }

    /**
     * Calculates the determinant of the matrix containing points' coordinates.
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
     * Calculates the determinant of the matrix containing coordinates of given
     * objects. Equivalent to:
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
     * Returns a cross product of two {@link Point}s.
     * 
     * @param first
     * @param other
     * @return double
     */
    public static double crossProduct(Point first, Point other) {
        return first.getX() * other.getX() + first.getY() * other.getY();
    }

    /**
     * Returns true if first {@link Point} is lexicografically smaller than the
     * other (checks X coordinate first, then Y).
     * 
     * @param first
     * @param other
     * @return boolean
     */
    public static boolean lessThanLexicalX(Point first, Point other) {
        return (first.getX() < other.getX()) || (first.getX() == other.getX() && first.getY() < other.getY());
    }

    /**
     * @param first
     * @param other
     * @return int
     */
    public static int compareToLexicalX(Point first, Point other) {
        if (lessThanLexicalX(first, other))
            return -1;
        else if (lessThanLexicalX(other, first))
            return 1;
        else
            return 0;
    }

    /**
     * Returns true if first {@link Point} is lexicografically smaller or equal to
     * the other (checks X coordinate first, then Y).
     * 
     * @param first
     * @param other
     * @return boolean
     */
    public static boolean lessEqualLexicalX(Point first, Point other) {
        return (first.getX() < other.getX()) || (first.getX() == other.getX() && first.getY() <= other.getY());
    }

    /**
     * Returns true if first {@link Point} is lexicografically smaller than the
     * other (checks Y coordinate first, then X).
     * 
     * @param first
     * @param other
     * @return boolean
     */
    public static boolean lessThanLexicalY(Point first, Point other) {
        return (first.getY() < other.getY()) || (first.getY() == other.getY() && first.getX() < other.getX());
    }

    /**
     * Returns true if first {@link Point} is lexicografically smaller or equal to
     * the other (checks Y coordinate first, then X).
     * 
     * @param first
     * @param other
     * @return boolean
     */
    public static boolean lessEqualLexicalY(Point first, Point other) {
        return (first.getY() < other.getY()) || (first.getY() == other.getY() && first.getX() <= other.getX());
    }

    /**
     * @param first
     * @param other
     * @return int
     */
    public static int compareToLexicalY(Point first, Point other) {
        if (lessThanLexicalY(first, other))
            return -1;
        else if (lessThanLexicalY(other, first))
            return 1;
        else
            return 0;
    }

    /**
     * Returns true if first {@link Point} has a smaller polar angle (when looking
     * at X axis) than the other.
     * 
     * @param first
     * @param other
     * @return boolean
     * @throws OriginPointException
     */
    public static boolean lessThanPolar(Point first, Point other)
            throws OriginPointException {
        return getPolar(first) < getPolar(other);
    }

    /**
     * Returns true if first {@link Point} has a smaller or equal polar angle (when
     * looking at X axis) than the other.
     * 
     * @param first
     * @param other
     * @return boolean
     * @throws OriginPointException
     */
    public static boolean lessEqualPolar(Point first, Point other)
            throws OriginPointException {
        return getPolar(first) <= getPolar(other);
    }

    /**
     * Returns: -1
     * 
     * @param first
     * @param other
     * @return int
     * @throws OriginPointException
     */
    public static int compareToPolar(Point first, Point other) throws OriginPointException {
        if (lessThanPolar(first, other))
            return -1;
        else if (lessThanPolar(other, first))
            return 1;
        else
            return 0;
    }
}
