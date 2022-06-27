import java.util.ArrayList;

public class Polygon implements Cloneable {
    private ArrayList<Point> _vertices;

    private double _determinant(Point a, Point b, Point c) {
        return ((a.getX() - c.getX()) * (b.getY() - a.getY())) - ((a.getX() - b.getX()) * (c.getY() - a.getY()));
    }

    private double _determinant(Point p, Segment s) {
        return _determinant(p, s.getA(), s.getB());
    }

    private boolean _isAdherent(Point p, Segment s) {
        if (_determinant(p, s) == 0.0) {
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

    private boolean _areIntersected(Segment first, Segment other) {
        double det_1 = _determinant(first.getA(), other) * _determinant(first.getB(), other),
                det_2 = _determinant(other.getA(), first) * _determinant(other.getB(), first);
        if (det_1 < 0 && det_2 < 0)
            return true;
        else if (det_1 == 0 && det_2 < 0) {
            if (_isAdherent(first.getA(), other) || _isAdherent(first.getB(), other))
                return true;
        } else if (det_1 < 0 && det_2 == 0) {
            if (_isAdherent(other.getA(), first) || _isAdherent(other.getB(), first))
                return true;
        } else if (det_1 == 0 && det_2 == 0) {
            if ((_isAdherent(first.getA(), other) || _isAdherent(first.getB(), other))
                    && (_isAdherent(other.getA(), first) || _isAdherent(other.getB(), first)))
                return true;
        }
        return false;
    }

    Polygon(Point[] vertices)
            throws InsufficientVerticesException,
            ColinearVerticesException,
            IdenticalVerticesException,
            IntersectingEdgesException {

        // checking if there are 3 or more vertices
        if (vertices.length < 3) {
            throw new InsufficientVerticesException("Polygon must have at least 3 vertices.");
        }

        // checking if no 3 subsequent vertices are in the same line
        Point pprev = vertices[vertices.length - 2], prev = vertices[vertices.length - 1];
        for (Point current : vertices) {

            if (_determinant(current, prev, pprev) == 0)
                throw new ColinearVerticesException("No 3 subsequent points shall lie on the same line.");
            pprev = prev;
            prev = current;
        }

        // creating a list of sides
        prev = vertices[vertices.length - 1];
        ArrayList<Segment> sides = new ArrayList<Segment>();
        for (int i = 0; i < vertices.length; ++i) {
            sides.add(new Segment(prev, vertices[i]));
            prev = vertices[i];
        }

        // the actual checking part
        // total amount of checks == ((n-3)*n)/2 for n >=3 vertices
        for (int i = 0, offset = 0; i < sides.size() - 2; ++i) {

            if (i == 0)
                offset = 1;
            else
                offset = 0;

            for (int j = i + 2; j < sides.size() - offset; ++j) {
                if (_areIntersected(sides.get(i), sides.get(j)))
                    throw new IntersectingEdgesException("No two edges shall intersect with each other.");

            }
        }

        // vertex assignment
        _vertices = new ArrayList<Point>();
        for (Point vertex : vertices) {
            _vertices.add(new Point(vertex));
        }
    }

    Polygon(ArrayList<Point> vertices)
            throws InsufficientVerticesException,
            ColinearVerticesException,
            IdenticalVerticesException,
            IntersectingEdgesException {
        this((Point[]) vertices.toArray(new Point[0]));

    }

    public int size() {
        return _vertices.size();
    }

    public ArrayList<Point> getVertices() throws CloneNotSupportedException {
        ArrayList<Point> result = new ArrayList<Point>();
        for (Point vertex : _vertices) {
            result.add(new Point(vertex));
        }
        return result;
    }

    public ArrayList<Segment> getSides() throws Exception {
        Point prev = _vertices.get(_vertices.size() - 1);
        ArrayList<Segment> sides = new ArrayList<Segment>();
        for (Point vertex : _vertices) {
            sides.add(new Segment(new Point(prev), new Point(vertex)));
            prev = vertex;
        }
        return sides;
    }

    public boolean isConcave() {
        if (_vertices.size() > 3) {
            Point pprev = new Point(_vertices.get(_vertices.size() - 2)),
                    prev = new Point(_vertices.get(_vertices.size() - 1));
            boolean more_than;
            if (_determinant(_vertices.get(0), prev, pprev) > 0)
                more_than = true;
            else
                more_than = false;
            for (int i = 1; i < _vertices.size(); ++i) {
                if (!(_determinant(_vertices.get(i), prev, pprev) < 0 ^ more_than))
                    return true;
                pprev = prev;
                prev = _vertices.get(i);
            }
        }
        return false;

    }

    @SuppressWarnings("unchecked")
    public Object clone() throws CloneNotSupportedException {
        Polygon cloned = (Polygon) super.clone();
        cloned._vertices = (ArrayList<Point>) _vertices.clone();
        return cloned;
    }
}
