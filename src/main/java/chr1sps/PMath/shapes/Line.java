package PMath.shapes;

import PMath.exceptions.IdenticalPointsException;

/**
 * This class serves as a way to represent a line on a Euclidian plane. A line
 * is represented by an equation of form Ax + By + C = 0.
 */
public class Line {
    private double _a, _b, _c;

    public Line(double A, double B, double C) {
        _a = A;
        _b = B;
        _c = C;
    }

    public Line(Point a, Point b) throws IdenticalPointsException {
        double delta_x = a.getX() - b.getX(), delta_y = a.getY() - b.getY();
        if (delta_x == 0 && delta_y == 0) {
            throw new IdenticalPointsException("Points defining the line must not be identical.");
        } else if (delta_x == 0) {
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

    public Line(Segment segment) throws IdenticalPointsException {
        this(segment.getA(), segment.getB());
    }

    public boolean isAdherent(Point point) {
        return _a * point.getX() + _b * point.getY() + _c == 0;
    }

    public boolean isAdherent(Segment segment) {
        return isAdherent(segment.getA()) && isAdherent(segment.getB());
    }
}
