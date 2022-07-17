package chr1sps.PMath.shapes;

import chr1sps.PMath.exceptions.IdenticalPointsException;
import chr1sps.PMath.utils.algorithms;

/**
 * This class serves as a way to represent a segment on a Euclidian plane. A
 * segment is defined by
 * two points (defined in the class as a and b), that represent two opposing
 * ends of a segment. These points must not have equal coordinates.
 */
public class Segment implements Cloneable {
    private Point _a, _b;

    public Segment(Point a, Point b) throws IdenticalPointsException {
        if (a.equals(b)) {
            throw new IdenticalPointsException("Points that make up a segment must differ.");
        }
        _a = new Point(a);
        _b = new Point(b);
    }

    public Segment(Segment other) {
        _a = new Point(other._a);
        _b = new Point(other._b);
    }

    /**
     * @return Point
     */
    public Point getA() {
        return new Point(_a);
    }

    /**
     * @return Point
     */
    public Point getB() {
        return new Point(_b);
    }

    /**
     * @param a
     * @throws IdenticalPointsException
     */
    public void setA(Point a) throws IdenticalPointsException {
        if (a.equals(_b))
            throw new IdenticalPointsException("New point A must differ from point B.");
        _a = new Point(a);
    }

    /**
     * @param b
     * @throws IdenticalPointsException
     */
    public void setB(Point b) throws IdenticalPointsException {
        if (b.equals(_a))
            throw new IdenticalPointsException("New point B must differ from point A.");
        _b = new Point(b);
    }

    /**
     * Returns the length of this {@link Segment}.
     * 
     * @return double
     */
    public double getLength() {
        double delta_x = _b.getX() - _a.getX(), delta_y = _b.getY() - _a.getY();
        double result = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return result;
    }

    /**
     * Returns true if the given {@link Point} lies on this {@link Segment}.
     * 
     * @param point
     * @return boolean
     */
    public boolean isAdherent(Point point) {
        if (algorithms.determinant(point, this) == 0.0) {
            if (_b.getX() - _a.getX() == 0.0) {
                if (point.getY() >= Math.min(_a.getY(), _b.getY()) && point.getY() <= Math.max(_a.getY(), _b.getY()))
                    return true;
            } else {
                if (point.getX() >= Math.min(_a.getX(), _b.getX()) && point.getX() <= Math.max(_a.getX(), _b.getX()))
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the given {@link Segment} lies on this {@link Segment}.
     * 
     * @param segment
     * @return boolean
     */
    public boolean isAdherent(Segment segment) {
        return isAdherent(segment.getA()) && isAdherent(segment.getB());
    }

    /**
     * Returns true is the segment is intersected with another segment.
     * 
     * @param other
     * @return boolean
     */
    public boolean isIntersected(Segment other) {
        double det_1 = algorithms.determinant(_a, other) * algorithms.determinant(_b, other),
                det_2 = algorithms.determinant(other._a, this) * algorithms.determinant(other._b, this);
        if (det_1 < 0 && det_2 < 0)
            return true;
        else if (det_1 == 0 && det_2 < 0) {
            if (other.isAdherent(_a) || other.isAdherent(_b))
                return true;
        } else if (det_1 < 0 && det_2 == 0) {
            if (isAdherent(other._a) || isAdherent(other._b))
                return true;
        } else if (det_1 == 0 && det_2 == 0) {
            if ((other.isAdherent(_a) || other.isAdherent(_b))
                    && (isAdherent(other._a) || isAdherent(other._b)))
                return true;
        }
        return false;
    }

    /**
     * Returns true when a given {@link Point} is colinear with this
     * {@link Segment}.
     * 
     * @param point
     * @return boolean
     */
    public boolean isColinear(Point point) {
        return algorithms.determinant(point, this) == 0;
    }

    /**
     * Returns true when a given {@link Segment} is colinear with this
     * {@link Segment}.
     * 
     * @param segment
     * @return boolean
     */
    public boolean isColinear(Segment segment) {
        return isColinear(segment.getA()) && isColinear(segment.getB());
    }

    /**
     * @return Object
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        Segment cloned = (Segment) super.clone();
        cloned._a = (Point) _a.clone();
        cloned._b = (Point) _b.clone();
        return cloned;
    }

    /**
     * Returns a string containing data about the segment. Example result:
     * 
     * <pre>
     * "(0.0000, 1.0000)-(1.0000, 1.0000)"
     * </pre>
     * 
     * @return String
     */
    public String toString() {
        return _a.toString() + '-' + _b.toString();
    }

    /**
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + _a.hashCode();
        result = prime * result + _b.hashCode();
        return result;
    }

    /**
     * Returns true if the given object is a {@link Segment} with a and b points
     * equal to this {@link Segment}s a and b points, respectively.
     * 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Segment other = (Segment) obj;
        if (!_a.equals(other._a))
            return false;
        if (!_b.equals(other._b))
            return false;
        return true;
    }

    /**
     * Returns true if the given object is a {@link Segment} with a and b points
     * equal to this {@link Segment}s a and b points, no matter the order (a and b
     * can be switched).
     * 
     * @param obj
     * @return boolean
     */
    public boolean equalsIgnoreOrder(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Segment other = (Segment) obj;
        if (_a.equals(other._a) && _b.equals(other._b))
            return true;
        if (_a.equals(other._b) && _b.equals(other._a))
            return true;
        return false;
    }
}
