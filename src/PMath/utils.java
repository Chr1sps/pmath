package PMath;

import PMath.shapes.Point;
import PMath.shapes.Segment;

public class utils {
    public static double determinant(Point a, Point b, Point c) {
        return ((a.getX() - c.getX()) * (b.getY() - a.getY())) - ((a.getX() - b.getX()) * (c.getY() - a.getY()));
    }

    public static double determinant(Point p, Segment s) {
        return determinant(p, s.getA(), s.getB());
    }
}
