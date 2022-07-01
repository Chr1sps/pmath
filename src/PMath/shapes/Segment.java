package PMath.shapes;

import PMath.exceptions.IdenticalVerticesException;
import PMath.utils;

public class Segment implements Cloneable {
    private Point _a, _b;

    public Segment(Point a, Point b) throws IdenticalVerticesException {
        if (a.equals(b)) {
            throw new IdenticalVerticesException("Points must differ.");
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
     * @throws IdenticalVerticesException
     */
    public void setA(Point a) throws IdenticalVerticesException {
        if (a.equals(_b))
            throw new IdenticalVerticesException("New point A must differ from point B.");
        _a = new Point(a);
    }

    /**
     * @param b
     * @throws IdenticalVerticesException
     */
    public void setB(Point b) throws IdenticalVerticesException {
        if (b.equals(_a))
            throw new IdenticalVerticesException("New point B must differ from point A.");
        _b = new Point(b);
    }

    /**
     * @return double
     */
    public double getLength() {
        double delta_x = _b.getX() - _a.getX(), delta_y = _b.getY() - _a.getY();
        double result = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return result;
    }

    /**
     * Returns true if the given point lies on the segment.
     * 
     * @param p
     * @return boolean
     */
    public boolean isAdherent(Point p) {
        if (utils.determinant(p, this) == 0.0) {
            if (_b.getX() - _a.getX() == 0.0) {
                if (p.getY() >= Math.min(_a.getY(), _b.getY()) && p.getY() <= Math.max(_a.getY(), _b.getY()))
                    return true;
            } else {
                if (p.getX() >= Math.min(_a.getX(), _b.getX()) && p.getX() <= Math.max(_a.getX(), _b.getX()))
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns true is the segment is intersected with another segment.
     * 
     * @param other
     * @return boolean
     */
    public boolean isIntersected(Segment other) {
        double det_1 = utils.determinant(_a, other) * utils.determinant(_b, other),
                det_2 = utils.determinant(other._a, this) * utils.determinant(other._b, this);
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
}
