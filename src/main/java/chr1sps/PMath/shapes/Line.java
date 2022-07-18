package chr1sps.PMath.shapes;

import chr1sps.PMath.exceptions.IdenticalPointsException;
import chr1sps.PMath.exceptions.InvalidCoefficientsException;

/**
 * This class serves as a way to represent a line on a Euclidian plane. A line
 * is represented by an equation of form Ax + By + C = 0.
 */
public class Line implements Cloneable {
    private double _a, _b, _c;

    private void _initiateLine(Point a, Point b) {
        double delta_x = a.getX() - b.getX(), delta_y = a.getY() - b.getY();
        if (delta_x == 0) {
            _a = 1;
            _b = 0;
            _c = -a.getX();
        } else if (delta_y == 0) {
            _a = 0;
            _b = 1;
            _c = -a.getY();
        } else {
            _a = 1;
            _b = -(delta_x / delta_y);
            _c = -a.getX() + a.getY() * (delta_x / delta_y);
        }
    }

    public Line(double A, double B, double C) throws InvalidCoefficientsException {
        if (A == 0 && B == 0)
            throw new InvalidCoefficientsException("One of A or B must be non-zero.");
        else if (A == 0) {
            _a = 0;
            _b = 1;
            _c = C / B;
        } else if (B == 0) {
            _a = 1;
            _b = 0;
            _c = C / A;
        } else {
            _a = 1;
            _b = B / A;
            _c = C / A;
        }

    }

    public Line(Point a, Point b) throws IdenticalPointsException {
        if (a.getX() - b.getX() == 0 && a.getY() - b.getY() == 0)
            throw new IdenticalPointsException("Points defining the line must not be identical.");
        _initiateLine(a, b);
    }

    public Line(Segment segment) {
        _initiateLine(segment.getA(), segment.getB());
    }

    public Line(Line other) {
        _a = other._a;
        _b = other._b;
        _c = other._c;
    }

    public boolean isAdherent(Point point) {
        return _a * point.getX() + _b * point.getY() + _c == 0;
    }

    public boolean isAdherent(Segment segment) {
        return isAdherent(segment.getA()) && isAdherent(segment.getB());
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Double[] arr = { _a, _b, _c };
        for (Double temp : arr) {
            result = prime * result + temp.hashCode();
        }
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Line other = (Line) obj;
        if (_a != other._a)
            return false;
        if (_b != other._b)
            return false;
        if (_c != other._c)
            return false;
        return true;
    }

    private String _formatCoefficient(double d) {
        if (d == 0)
            return "";
        else if (d < 0)
            return String.format(" - %.4f", -d);
        else
            return String.format(" + %.4f", d);
    }

    public Object clone() throws CloneNotSupportedException {
        Line cloned = (Line) super.clone();
        cloned._a = _a;
        cloned._b = _b;
        cloned._c = _c;
        return cloned;
    }

    public String toString() {
        if (_a == 1) {
            return String.format("[1.0000x%sy%s = 0]", _formatCoefficient(_b), _formatCoefficient(_c));
        } else {
            return String.format("[1.0000y%s = 0]", _formatCoefficient(_c));
        }
    }
}
