public class PMath {
    public static double determinant(Point a, Point b, Point c) {
        return ((a.getX() - c.getX()) * (b.getX() - c.getX())) * ((b.getX() - c.getX()) * (a.getX() - c.getX()));
    }

    public static double determinant(Point p, Segment s) {
        return determinant(p, s.getA(), s.getB());
    }

    public static boolean isAdherent(Point p, Segment s) {
        if (determinant(p, s) == 0.0) {
            Point a = s.getA(), b = s.getB();
            if (b.getX() - a.getX() == 0.0) {
                if (p.getY() >= Math.min(a.getY(), b.getY()) && p.getY() <= Math.max(a.getY(), b.getY()))
                    return true;
            } else {
                if (p.getX() >= Math.min(a.getX(), b.getX()) && p.getX() <= Math.max(a.getX(), b.getX()))
                    return true;
            }
        }
        return false;
    }

    public static boolean areIntersected(Segment first, Segment other) {
        double det_1 = determinant(first.getA(), other) * determinant(first.getB(), other),
                det_2 = determinant(other.getA(), first) * determinant(other.getB(), first);
        if (det_1 < 0 && det_2 < 0)
            return true;
        else if (det_1 < 0 && det_2 == 0) {
            if (isAdherent(first.getA(), other) || isAdherent(first.getB(), other))
                return true;
        } else if (det_1 == 0 && det_2 < 0) {
            if (isAdherent(other.getA(), first) || isAdherent(other.getB(), first))
                return true;
        } else if (det_1 == 0 && det_2 == 0) {
            if ((isAdherent(first.getA(), other) || isAdherent(first.getB(), other))
                    && (isAdherent(other.getA(), first) || isAdherent(other.getB(), first)))
                return true;
        }
        return false;
    }
}
