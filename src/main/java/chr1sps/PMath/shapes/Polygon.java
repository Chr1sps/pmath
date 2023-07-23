package chr1sps.PMath.shapes;

import static chr1sps.PMath.utils.algorithms.determinant;
import static chr1sps.PMath.utils.algorithms.lessThanLexicalX;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import chr1sps.PMath.exceptions.ColinearVerticesException;
import chr1sps.PMath.exceptions.IdenticalPointsException;
import chr1sps.PMath.exceptions.InsufficientVerticesException;
import chr1sps.PMath.exceptions.IntersectingEdgesException;

/**
 * This class serves as a way to represent a polygon on a Euclidian plane. A
 * polygon is defined by a list of vertices. Each vertex on a list is connected
 * to the one before and after (with the last one and the first one also being
 * connected). There are three conditions that a list of vertices must meet in
 * order to represent a valid polygon:
 * <p>
 * - there must be at least 3 vertices in the list
 * <p>
 * - no three subsequent vertices shall lie on the same line
 * <p>
 * - no two non-subsequent edges shall intersect with each other (this includes
 * being connected via a shared vertex and being colinear).
 */
public class Polygon implements Cloneable {
    private ArrayList<Point> _vertices;

    /**
     * This method is used to manipulate the input vertex array in order to get an
     * unambiguous representation of a polygon in an ArrayList object that is then
     * passed to the _vertices ArrayList.
     *
     * @param list array of {@link Point}s to assign
     */
    private void _shiftReverseAndAssign(Point[] list) {
        _vertices = new ArrayList<>();
        try {
            for (Point vertex : list) {
                _vertices.add((Point) vertex.clone());
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
        Point smallest = _vertices.get(0), temp;

        // finding the lexicografically smallest Point
        for (Point vertex : _vertices) {
            if (lessThanLexicalX(vertex, smallest)) {
                smallest = vertex;
            }
        }

        // shifting the list so that the smallest object is at the beginning
        while (_vertices.indexOf(smallest) != 0) {
            temp = _vertices.remove(0);
            _vertices.add(temp);
        }

        // comparing two adjacent vertices and choosing the lexicographically smaller
        // one to set up the order in the _vertices arraylist
        Point adj_1 = _vertices.get(1), adj_2 = _vertices.get(_vertices.size() - 1);
        if (determinant(adj_1, _vertices.get(0), adj_2) > 0) {
            ArrayList<Point> temp_list = new ArrayList<>();
            while (_vertices.size() != 1) {
                temp_list.add(_vertices.remove(_vertices.size() - 1));
            }
            _vertices.addAll(temp_list);
        }
    }

    /**
     * This method can ONLY be used by convex polygons. Checks if a given point is
     * inside of a convex polygon.
     *
     * @param point
     * @return boolean
     */
    private boolean _isInsideConvex(Point point) {
        int p = 0, k = _vertices.size() - 2, s = k / 2;
        for (; k - p != 1; s = (k + p) / 2) {
            if (determinant(_vertices.get(_vertices.size() - 1), _vertices.get(s), point)
                    * determinant(
                    _vertices.get(_vertices.size() - 1), _vertices.get(s),
                    _vertices.get(_vertices.size() - 2)) > 0)
                p = s;
            else
                k = s;
        }

        try {
            Segment[] sides = {new Segment(_vertices.get(_vertices.size() - 1), _vertices.get(p)),
                    new Segment(_vertices.get(p), _vertices.get(k)),
                    new Segment(_vertices.get(k), _vertices.get(_vertices.size() - 1))};
            int sum_det = 0;
            for (Segment side : sides) {
                if (side.isAdherent(point))
                    return true;
                else if ((determinant(point, side)) > 0)
                    ++sum_det;
                else
                    --sum_det;
            }
            return sum_det == 3 || sum_det == -3;
        } catch (IdenticalPointsException e) {
            throw new RuntimeException();
        }
    }

    public Polygon(Point[] vertices)
            throws InsufficientVerticesException,
            ColinearVerticesException,
            IntersectingEdgesException {

        // checking if there are 3 or more vertices
        if (vertices.length < 3) {
            throw new InsufficientVerticesException(
                    "Polygon must have at least 3 vertices. Amount of vertices given: " + vertices.length + ".");
        }

        // checking if no 3 subsequent vertices are in the same line
        Point pprev = vertices[vertices.length - 2], prev = vertices[vertices.length - 1];
        for (Point current : vertices) {

            if (determinant(current, prev, pprev) == 0)
                throw new ColinearVerticesException("No 3 subsequent points shall lie on the same line.");
            pprev = prev;
            prev = current;
        }

        // creating a list of sides
        prev = vertices[vertices.length - 1];
        ArrayList<Segment> sides = new ArrayList<>();
        try {
            for (Point vertex : vertices) {
                sides.add(new Segment(prev, vertex));
                prev = vertex;
            }
        } catch (IdenticalPointsException e) {
            throw new RuntimeException();
        }

        // the actual checking part
        // total amount of checks == ((n-3)*n)/2 for n >=3 vertices
        for (int i = 0, offset; i < sides.size() - 2; ++i) {

            if (i == 0)
                offset = 1;
            else
                offset = 0;

            for (int j = i + 2; j < sides.size() - offset; ++j) {
                if (sides.get(i).isIntersected(sides.get(j)))
                    throw new IntersectingEdgesException("No two edges shall intersect with each other.");

            }
        }

        // vertex assignment
        _shiftReverseAndAssign(vertices);
    }

    public Polygon(ArrayList<Point> vertices)
            throws InsufficientVerticesException,
            ColinearVerticesException,
            IntersectingEdgesException {
        this(vertices.toArray(new Point[0]));

    }

    @SuppressWarnings("unchecked")
    public Polygon(Polygon other) {
        _vertices = (ArrayList<Point>) other._vertices.clone();
    }

    /**
     * Returns the amount of vertices in the polygon.
     *
     * @return int
     */
    public int size() {
        return _vertices.size();
    }

    /**
     * Returns a deep copy of the ArrayList containing all the vertices.
     *
     * @return ArrayList<Point>
     */
    public ArrayList<Point> getVertices() {
        ArrayList<Point> result = new ArrayList<>();
        for (Point vertex : _vertices) {
            result.add(new Point(vertex));
        }
        return result;
    }

    /**
     * Returns an ArrayList containing all the polygon's {@link Segment}s (note: the
     * list
     * starts from the {@link Segment} with vertices numbered [size - 1, 0]
     * (ordering from
     * 0)).
     *
     * @return ArrayList<Segment>
     */
    public ArrayList<Segment> getSides() {
        Point prev = _vertices.get(_vertices.size() - 1);
        ArrayList<Segment> sides = new ArrayList<>();
        for (Point vertex : _vertices) {
            try {
                sides.add(new Segment(new Point(prev), new Point(vertex)));
            } catch (IdenticalPointsException e) {
                throw new RuntimeException();
            }
            prev = vertex;
        }
        return sides;
    }

    /**
     * Returns a polygon's circumference.
     *
     * @return double
     */
    public double getCircumference() {
        double result = 0;
        for (Segment seg : getSides()) {
            result += seg.getLength();
        }
        return result;
    }

    /**
     * Returns true if a polygon is concave.
     *
     * @return boolean
     */
    public boolean isConcave() {
        if (_vertices.size() > 3) {
            Point pprev = new Point(_vertices.get(_vertices.size() - 2)),
                    prev = new Point(_vertices.get(_vertices.size() - 1));
            boolean more_than = determinant(_vertices.get(0), prev, pprev) > 0;
            for (int i = 1; i < _vertices.size(); ++i) {
                if (determinant(_vertices.get(i), prev, pprev) < 0 == more_than)
                    return true;
                pprev = prev;
                prev = _vertices.get(i);
            }
        }
        return false;

    }

    /**
     * Checks if a given {@link Point} is inside of a polygon.
     *
     * @param point
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public boolean isInside(Point point) {
        ArrayList<Point> vertices_copy = (ArrayList<Point>) _vertices.clone(), stack = new ArrayList<>(),
                empty_space = new ArrayList<>();
        try {
            Point pivot = (Point) vertices_copy.get(0).clone();
            vertices_copy.remove(0);

            stack.add(pivot);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
        boolean concave = false;
        // hull part
        for (Point vertex : vertices_copy) {

            // checking if a point belongs to an edge
            try {
                if (new Segment(stack.get(stack.size() - 1), vertex).isAdherent(point))
                    return true;
            } catch (IdenticalPointsException e) {
                throw new RuntimeException();
            }

            // if stack has >1 point on it and the determinant is < 0 then create a list of
            // vertices that define the empty space
            if (stack.size() > 1) {
                for (empty_space.add(vertex); stack.size() > 1 &&
                        determinant(stack.get(stack.size() - 2),
                                stack.get(stack.size() - 1), vertex) < 0.0; concave = true) {
                    empty_space.add(stack.remove(stack.size() - 1));
                }
            }

            // if there was an empty space, concave will be set to true and there will be a
            // recursive call to this method using a new Polygon object created from the
            // empty space vertices
            if (concave) {
                empty_space.add(stack.get(stack.size() - 1));
                concave = false;
                try {
                    if (new Polygon(empty_space).isInside(point))
                        return false;
                } catch (InsufficientVerticesException | ColinearVerticesException | IntersectingEdgesException e) {
                    throw new RuntimeException();
                }
                empty_space.clear();
            }

            // after handling all the scenarios, add the vertex
            stack.add(vertex);
        }

        // resulting polygon in the stack is a convex hull, therefore we can use a
        // normal algorithm used for convex polygons
        try {
            return new Polygon(stack)._isInsideConvex(point);
        } catch (InsufficientVerticesException | ColinearVerticesException | IntersectingEdgesException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Returns a pair of {@link Point}s signifying the span of coordinate values on
     * which a polygon is located.
     *
     * @return Entry<Point, Point>
     */
    public Map.Entry<Point, Point> getSpan() {
        Point min = new Point(_vertices.get(0)), max = new Point(_vertices.get(0));
        for (Point point : _vertices) {
            if (point.getX() < min.getX())
                min.setX(point.getX());
            if (point.getY() < min.getY())
                min.setY(point.getY());
            if (point.getX() > max.getX())
                max.setX(point.getX());
            if (point.getY() > max.getY())
                max.setY(point.getY());
        }
        return new AbstractMap.SimpleImmutableEntry<>(min, max);
    }

    /**
     * @return Object
     * @throws CloneNotSupportedException
     */
    @SuppressWarnings("unchecked")
    public Object clone() throws CloneNotSupportedException {
        Polygon cloned = (Polygon) super.clone();
        cloned._vertices = (ArrayList<Point>) _vertices.clone();
        return cloned;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (Point vertex : _vertices) {
            result = prime * result + vertex.hashCode();
        }
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
        Polygon other = (Polygon) obj;
        return _vertices.equals(other._vertices);
    }

    /**
     * Returns a string containing data about the polygon. Example result:
     *
     * <pre>
     * "0: (0.0000, 0.0000)
     * 1: (0.0000, 1.0000)
     * 2: (1.0000, 1.0000)
     * 3: (1.0000, 0.0000)"
     * </pre>
     *
     * @return String
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < _vertices.size(); ++i) {
            result += String.format("%d: %s\n", i, _vertices.get(i).toString());
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }
}
