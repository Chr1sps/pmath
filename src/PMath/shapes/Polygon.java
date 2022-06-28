package PMath.shapes;

import java.util.ArrayList;

import PMath.exceptions.ColinearVerticesException;
import PMath.exceptions.IdenticalVerticesException;
import PMath.exceptions.InsufficientVerticesException;
import PMath.exceptions.IntersectingEdgesException;
import PMath.utils;

public class Polygon implements Cloneable {
    private ArrayList<Point> _vertices;

    public Polygon(Point[] vertices)
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

            if (utils.determinant(current, prev, pprev) == 0)
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
                if (utils.areIntersected(sides.get(i), sides.get(j)))
                    throw new IntersectingEdgesException("No two edges shall intersect with each other.");

            }
        }

        // vertex assignment
        _vertices = new ArrayList<Point>();
        for (Point vertex : vertices) {
            _vertices.add(new Point(vertex));
        }
    }

    public Polygon(ArrayList<Point> vertices)
            throws InsufficientVerticesException,
            ColinearVerticesException,
            IdenticalVerticesException,
            IntersectingEdgesException {
        this((Point[]) vertices.toArray(new Point[0]));

    }

    @SuppressWarnings("unchecked")
    public Polygon(Polygon other) {
        _vertices = (ArrayList<Point>) other._vertices.clone();
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
            if (utils.determinant(_vertices.get(0), prev, pprev) > 0)
                more_than = true;
            else
                more_than = false;
            for (int i = 1; i < _vertices.size(); ++i) {
                if (!(utils.determinant(_vertices.get(i), prev, pprev) < 0 ^ more_than))
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (Point vertex : _vertices) {
            result = prime * result + vertex.hashCode();
        }
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
        Polygon other = (Polygon) obj;
        if (_vertices.equals(other._vertices))
            return true;
        return false;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < _vertices.size(); ++i) {
            result += String.format("%d: %s\n", i, _vertices.get(i).toString());
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }
}
