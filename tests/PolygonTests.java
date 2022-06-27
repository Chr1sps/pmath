import java.util.ArrayList;
import org.junit.*;

public class PolygonTests {
    private static Point point_1, point_2, point_3, point_4, point_5;
    private static ArrayList<Point> vertices;

    @BeforeClass
    public static void setUp() {
        point_1 = new Point(0.0, 0.0);
        point_2 = new Point(0.0, 1.0);
        point_3 = new Point(1.0, 1.0);
        point_4 = new Point(1.0, 0.0);
        point_5 = new Point(0.5, 0.5);
        vertices = new ArrayList<Point>();
    }

    @Test
    public void testInitValid() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4 };
        for (Point i : arr) {
            vertices.add(i);
        }
        Polygon poly = new Polygon(vertices);
        Assert.assertEquals(4, poly.size());

    }

    @Test(expected = InsufficientVerticesException.class)
    public void testInitInsufficientVerticesException() throws Exception {
        Point[] arr = { point_1, point_2 };
        for (Point i : arr) {
            vertices.add(i);
        }
        new Polygon(vertices);
    }

    @Test(expected = ColinearVerticesException.class)
    public void testInitColinearVerticesException() throws Exception {
        Point[] arr = { point_1, point_3, point_5 };
        for (Point i : arr) {
            vertices.add(i);
        }
        new Polygon(vertices);
    }

    @Test(expected = IntersectingEdgesException.class)
    public void testInitIntersectingEdgesException() throws Exception {
        Point[] arr = { point_1, point_2, point_4, point_3 };
        for (Point i : arr) {
            vertices.add(i);
        }
        new Polygon(vertices);
    }

    @Test
    public void testGetVertices() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4 };
        for (Point i : arr) {
            vertices.add(i);
        }
        Polygon poly = new Polygon(vertices);
        ArrayList<Point> result = poly.getVertices();
        result.equals(vertices);
    }

    @Test
    public void testGetSides() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4 };
        ArrayList<Segment> edges = new ArrayList<>(), result;
        Point prev = point_4;
        for (Point current : arr) {
            edges.add(new Segment(prev, current));
            vertices.add(current);
            prev = current;
        }
        Polygon poly = new Polygon(vertices);
        result = poly.getSides();
        result.equals(edges);
    }
}
