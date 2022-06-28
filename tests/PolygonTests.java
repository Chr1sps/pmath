import java.util.ArrayList;
import org.junit.*;

import PMath.shapes.*;
import PMath.exceptions.*;

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

    @Test
    public void testCopyingConstructor() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4 };
        for (Point i : arr) {
            vertices.add(i);
        }
        Polygon poly = new Polygon(vertices), poly_new = new Polygon(poly);
        Assert.assertEquals(4, poly_new.size());
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

    @Test
    public void testIsConcaveFalse() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4 };
        for (Point i : arr) {
            vertices.add(i);
        }
        Polygon poly = new Polygon(vertices);
        Assert.assertFalse(poly.isConcave());
    }

    @Test
    public void testIsConcaveTrue() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4, point_5 };
        for (Point i : arr) {
            vertices.add(i);
        }
        Polygon poly = new Polygon(vertices);
        Assert.assertTrue(poly.isConcave());
    }

    @Test
    public void testClone() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4 };
        for (Point i : arr) {
            vertices.add(i);
        }
        Polygon poly = new Polygon(vertices), poly_new = (Polygon) poly.clone();
        Assert.assertEquals(4, poly_new.size());
    }

    @Test
    public void testHashCode() throws Exception {
        Point[] arr_1 = { point_1, point_2, point_3 }, arr_2 = { point_1, point_2, point_4 };
        Polygon poly_1 = new Polygon(arr_1), poly_2 = new Polygon(arr_2);
        Assert.assertNotEquals(poly_1.hashCode(), poly_2.hashCode());
    }

    @Test
    public void testEqualsTrue() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4 };
        for (Point i : arr) {
            vertices.add(i);
        }
        Polygon poly_1 = new Polygon(vertices), poly_2 = new Polygon(vertices);
        Assert.assertTrue(poly_1.equals(poly_2));
    }

    @Test
    public void testEqualsFalse() throws Exception {
        Point[] arr_1 = { point_1, point_2, point_3 }, arr_2 = { point_1, point_2, point_4 };
        Polygon poly_1 = new Polygon(arr_1), poly_2 = new Polygon(arr_2);
        Assert.assertFalse(poly_1.equals(poly_2));
    }

    @Test
    public void testEqualsDifferentOrder() throws Exception {
        Point[] arr_1 = { point_1, point_2, point_3, point_4 }, arr_2 = { point_2, point_3, point_4, point_1 };
        Polygon poly_1 = new Polygon(arr_1), poly_2 = new Polygon(arr_2);
        Assert.assertTrue(poly_1.equals(poly_2));
    }

    @Test
    public void testEqualsReversedOrder() throws Exception {
        Point[] arr_1 = { point_1, point_2, point_3, point_4 }, arr_2 = { point_1, point_4, point_3, point_2 };
        Polygon poly_1 = new Polygon(arr_1), poly_2 = new Polygon(arr_2);
        Assert.assertTrue(poly_1.equals(poly_2));
    }

    @Test
    public void testToString() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4 };
        Polygon poly = new Polygon(arr);
        Assert.assertEquals("0: (0.0000, 0.0000)\n1: (1.0000, 0.0000)\n2: (1.0000, 1.0000)\n3: (0.0000, 1.0000)",
                poly.toString());
    }
}
