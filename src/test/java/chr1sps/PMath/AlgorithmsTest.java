package chr1sps.PMath;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import chr1sps.PMath.exceptions.OriginPointException;
import chr1sps.PMath.shapes.Point;
import chr1sps.PMath.shapes.Segment;
import chr1sps.PMath.utils.algorithms;

public class AlgorithmsTest {
    private static Point zero, a1, a2, a3, b1, b2, b3;

    @BeforeClass
    public static void setUp() {
        zero = new Point(0, 0);
        a1 = new Point(1, 2);
        a2 = new Point(0, 4);
        a3 = new Point(0, 1);
        b1 = new Point(3, 4);
        b2 = new Point(4, 3);
        b3 = new Point(2, 3);
    }

    @Test
    public void testDeterminant() throws Exception {
        Assert.assertEquals(6.0, algorithms.determinant(a2, a1, b1), 0.0);
        Assert.assertEquals(9.0, algorithms.determinant(a2, a3, b1), 0.0);
        Assert.assertEquals(-2.0, algorithms.determinant(b2, new Segment(b3, a2)), 0.0);
        Assert.assertEquals(0.0, algorithms.determinant(a3, new Segment(a1, b1)), 0.0);
    }

    @Test
    public void testCrossProduct() throws Exception {
        Assert.assertEquals(11, algorithms.crossProduct(a1, b1), 0);
        Assert.assertEquals(4, algorithms.crossProduct(a2, a3), 0);
        Assert.assertEquals(3, algorithms.crossProduct(b2, a3), 0);

    }

    @Test
    public void testLessThanLexicalX() throws Exception {
        Assert.assertTrue(algorithms.lessThanLexicalX(a1, b1));
        Assert.assertTrue(algorithms.lessThanLexicalX(a3, a2));
        Assert.assertFalse(algorithms.lessThanLexicalX(a1, a1));
        Assert.assertFalse(algorithms.lessThanLexicalX(b2, b3));
    }

    @Test
    public void testLessEqualLexicalX() throws Exception {
        Assert.assertTrue(algorithms.lessEqualLexicalX(a1, b1));
        Assert.assertTrue(algorithms.lessEqualLexicalX(a3, a2));
        Assert.assertTrue(algorithms.lessEqualLexicalX(a1, a1));
        Assert.assertFalse(algorithms.lessEqualLexicalX(b2, b3));
    }

    @Test
    public void testLessThanLexicalY() throws Exception {
        Assert.assertTrue(algorithms.lessThanLexicalY(a1, b1));
        Assert.assertTrue(algorithms.lessThanLexicalY(a3, a2));
        Assert.assertFalse(algorithms.lessThanLexicalY(a1, a1));
        Assert.assertFalse(algorithms.lessThanLexicalY(b2, b3));
    }

    @Test
    public void testLessEqualLexicalY() throws Exception {
        Assert.assertTrue(algorithms.lessEqualLexicalY(a1, b1));
        Assert.assertTrue(algorithms.lessEqualLexicalY(a3, a2));
        Assert.assertTrue(algorithms.lessEqualLexicalY(a1, a1));
        Assert.assertFalse(algorithms.lessEqualLexicalY(b2, b3));
    }

    @Test
    public void testLessThanPolar() throws Exception {
        Assert.assertTrue(algorithms.lessThanPolar(b1, a1));
        Assert.assertFalse(algorithms.lessThanPolar(a3, a2));
        Assert.assertFalse(algorithms.lessThanPolar(a1, a1));
        Assert.assertTrue(algorithms.lessThanPolar(b2, b3));
    }

    @Test
    public void testLessEqualPolar() throws Exception {
        Assert.assertTrue(algorithms.lessEqualPolar(b1, a1));
        Assert.assertTrue(algorithms.lessEqualPolar(a3, a2));
        Assert.assertTrue(algorithms.lessEqualPolar(a1, a1));
        Assert.assertTrue(algorithms.lessEqualPolar(b2, b3));
    }

    @Test(expected = OriginPointException.class)
    public void testLessEqualPolarException() throws Exception {
        algorithms.lessEqualPolar(zero, a1);
    }

    @Test(expected = OriginPointException.class)
    public void testLessThanPolarException() throws Exception {
        algorithms.lessThanPolar(zero, a1);
    }
}