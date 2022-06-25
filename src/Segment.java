public class Segment {
    private Point _a, _b;

    Segment(Point a, Point b) throws Exception {
        if (a.equals(b)) {
            throw new Exception("Points must differ.");
        }
        _a = a;
        _b = b;
    }

    Segment(Segment other) {
        _a = other._a;
        _b = other._b;
    }

    public Point getA() {
        return _a;
    }

    public Point getB() {
        return _b;
    }

    public void setA(Point a) throws Exception {
        if (a.equals(_b))
            throw new Exception("New point A must differ from point B.");
        _a = a;
    }

    public void setB(Point b) throws Exception {
        if (b.equals(_a))
            throw new Exception("New point B must differ from point A.");
        _b = b;
    }

    public double getLength() {
        double delta_x = _b.getX() - _a.getX(), delta_y = _b.getY() - _a.getY();
        double result = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return result;
    }
}
