package chr1sps.PMath;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import chr1sps.PMath.exceptions.ColinearVerticesException;
import chr1sps.PMath.exceptions.InsufficientVerticesException;
import chr1sps.PMath.exceptions.IntersectingEdgesException;
import chr1sps.PMath.shapes.Point;
import chr1sps.PMath.shapes.Polygon;
import chr1sps.PMath.shapes.Segment;

public class PolygonTest {
    private static Point point_1, point_2, point_3, point_4, point_5, point_6, point_7;
    private static ArrayList<Point> vertices;

    @BeforeClass
    public static void setUp() {
        point_1 = new Point(0.0, 0.0);
        point_2 = new Point(0.0, 1.0);
        point_3 = new Point(1.0, 1.0);
        point_4 = new Point(1.0, 0.0);
        point_5 = new Point(0.5, 0.5);
        point_6 = new Point(0.25, 0.25);
        point_7 = new Point(0.5, 0.25);
    }

    @Before
    public void clearVertices() {
        vertices = new ArrayList<Point>(0);
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
    public void testGetCircumference() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4 };
        Polygon poly = new Polygon(arr);
        Assert.assertEquals(4, poly.getCircumference(), 0.0);
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
    public void testIsInsideTrue() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4 };
        Polygon poly = new Polygon(arr);
        Assert.assertTrue(poly.isInside(point_5));
    }

    @Test
    public void testIsInsidePolygonVertex() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4 };
        Polygon poly = new Polygon(arr);
        Assert.assertTrue(poly.isInside(point_1));
    }

    @Test
    public void testIsInsideEdge() throws Exception {
        Point[] arr = { point_1, point_2, point_3 };
        Polygon poly = new Polygon(arr);
        Assert.assertTrue(poly.isInside(point_5));
    }

    @Test
    public void testIsInsideLexicographicalSortError() throws Exception {
        Point[] arr_1 = { point_1, point_7, point_3, point_4 }, arr_2 = { point_1, point_4, point_3, point_7 };
        Polygon poly_1 = new Polygon(arr_1), poly_2 = new Polygon(arr_2);
        Assert.assertTrue(poly_1.isInside(point_4));
        Assert.assertTrue(poly_2.isInside(point_4));

    }

    @Test
    public void testIsInsideFalse() throws Exception {
        Point[] arr = { point_1, point_2, point_3 };
        Polygon poly = new Polygon(arr);
        Assert.assertFalse(poly.isInside(point_4));
    }

    @Test
    public void testIsInsideFalseEdgeExtension() throws Exception {
        Point[] arr = { point_1, point_5, point_2 };
        Polygon poly = new Polygon(arr);
        Assert.assertFalse(poly.isInside(point_3));
    }

    @Test
    public void testIsInsideConvexTrue() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4, point_5 };
        Polygon poly = new Polygon(arr);
        Assert.assertTrue(poly.isInside(point_6));
    }

    @Test
    public void testIsInsideConvexFalse() throws Exception {
        Point[] arr = { point_1, point_2, point_3, point_4, point_5 };
        Polygon poly = new Polygon(arr);
        Assert.assertFalse(poly.isInside(point_7));
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
    public void testEqualsDeterminantCorrectForBothSides() throws Exception {
        Point[] arr_1 = { point_1, point_7, point_3, point_4 }, arr_2 = { point_1, point_4, point_3, point_7 };
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
