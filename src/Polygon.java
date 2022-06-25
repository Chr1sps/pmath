public class Polygon {
    Point[] vertices;

    Polygon(Point[] points) throws Exception {
        if (points.length < 3) {
            throw new Exception("Polygon must have at least 3 points.");
        }
        vertices = points;
    }

    Polygon(Segment[] sides) throws Exception {
        if (sides.length < 3) {
            throw new Exception("Polygon must have at least 3 sides.");
        }
        for (int i = 0; i < sides.length; ++i) {
            if (sides[i].getB() != sides[(i + 1) % sides.length].getA())
                throw new Exception("Sides must be connected to each other.");

        }
        vertices = new Point[sides.length];
        for (int i = 0; i < sides.length; ++i) {
            vertices[i] = sides[i].getA();
        }
    }
}
