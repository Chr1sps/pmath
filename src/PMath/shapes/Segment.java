package PMath.shapes;

import PMath.exceptions.IdenticalVerticesException;

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

    public Point getA() {
        return new Point(_a);
    }

    public Point getB() {
        return new Point(_b);
    }

    public void setA(Point a) throws IdenticalVerticesException {
        if (a.equals(_b))
            throw new IdenticalVerticesException("New point A must differ from point B.");
        _a = new Point(a);
    }

    public void setB(Point b) throws IdenticalVerticesException {
        if (b.equals(_a))
            throw new IdenticalVerticesException("New point B must differ from point A.");
        _b = new Point(b);
    }

    public double getLength() {
        double delta_x = _b.getX() - _a.getX(), delta_y = _b.getY() - _a.getY();
        double result = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return result;
    }

    public Object clone() throws CloneNotSupportedException {
        Segment cloned = (Segment) super.clone();
        cloned._a = (Point) _a.clone();
        cloned._b = (Point) _b.clone();
        return cloned;
    }

    public String toString() {
        return _a.toString() + '-' + _b.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + _a.hashCode();
        result = prime * result + _b.hashCode();
        return result;
    }

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
