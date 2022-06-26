public class Polygon implements Cloneable {
    private Point[] _vertices;

    private double _determinant(Point a, Point b, Point c) {
        return ((a.getX() - c.getX()) * (b.getY() - a.getY())) - ((a.getX() - b.getX()) * (c.getY() - a.getY()));
    }

    Polygon(Point[] vertices) throws Exception {

        // checking if there are 3 or more vertices
        if (vertices.length < 3) {
            throw new Exception("Polygon must have at least 3 vertices.");
        }

        // checking if no 3 subsequent vertices are in the same line
        Point pprev = vertices[vertices.length - 2], prev = vertices[vertices.length - 1];
        for (Point current : vertices) {

            if (_determinant(current, prev, pprev) == 0)
                throw new Exception("No 3 subsequent points shall lie on the same line.");
            pprev = prev;
            prev = current;
        }

        // TODO: checking if no 2 non-subsequent lines cross each other

        // vertex assignment
        _vertices = (Point[]) vertices.clone();
    }

    public Point[] getVerticesCopy() throws CloneNotSupportedException {
        Point[] result = new Point[_vertices.length];
        for (int i = 0; i < _vertices.length; ++i) {
            result[i] = (Point) _vertices[i].clone();
        }
        return result;
    }

    public boolean isConcave() {
        return false;
    }

    public Object clone() throws CloneNotSupportedException {
        Polygon cloned = (Polygon) super.clone();
        cloned._vertices = this._vertices.clone();
        return cloned;
    }
}
