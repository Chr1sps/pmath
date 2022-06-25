public class Segment {
    private Point a, b;

    Segment(Point x, Point y) throws Exception {
        if (x.equals(y)) {
            throw new Exception("Points must differ.");
        }
        a = x;
        b = y;
    }

    Segment(Segment other) {
        a = other.a;
        b = other.b;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public void setA(Point p) throws Exception {
        if (p.equals(b))
            throw new Exception("New point A must differ from point B.");
        a = p;
    }

    public void setB(Point p) throws Exception {
        if (p.equals(a))
            throw new Exception("New point B must differ from point A.");
        b = p;
    }

    public double getLength() {
        double delta_x = b.getX() - a.getX(), delta_y = b.getY() - a.getY();
        double result = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return result;
    }
}
