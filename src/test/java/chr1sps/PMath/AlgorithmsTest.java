package chr1sps.PMath;

import static chr1sps.PMath.utils.algorithms.crossProduct;
import static chr1sps.PMath.utils.algorithms.determinant;
import static chr1sps.PMath.utils.algorithms.lessEqualLexicalX;
import static chr1sps.PMath.utils.algorithms.lessEqualLexicalY;
import static chr1sps.PMath.utils.algorithms.lessEqualPolar;
import static chr1sps.PMath.utils.algorithms.lessThanLexicalX;
import static chr1sps.PMath.utils.algorithms.lessThanLexicalY;
import static chr1sps.PMath.utils.algorithms.lessThanPolar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import chr1sps.PMath.exceptions.OriginPointException;
import chr1sps.PMath.shapes.Point;
import chr1sps.PMath.shapes.Segment;

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
        assertEquals(6.0, determinant(a2, a1, b1), 0.0);
        assertEquals(9.0, determinant(a2, a3, b1), 0.0);
        assertEquals(-2.0, determinant(b2, new Segment(b3, a2)), 0.0);
        assertEquals(0.0, determinant(a3, new Segment(a1, b1)), 0.0);
    }

    @Test
    public void testCrossProduct() throws Exception {
        assertEquals(11, crossProduct(a1, b1), 0);
        assertEquals(4, crossProduct(a2, a3), 0);
        assertEquals(3, crossProduct(b2, a3), 0);

    }

    @Test
    public void testLessThanLexicalX() throws Exception {
        assertTrue(lessThanLexicalX(a1, b1));
        assertTrue(lessThanLexicalX(a3, a2));
        assertFalse(lessThanLexicalX(a1, a1));
        assertFalse(lessThanLexicalX(b2, b3));
    }

    @Test
    public void testLessEqualLexicalX() throws Exception {
        assertTrue(lessEqualLexicalX(a1, b1));
        assertTrue(lessEqualLexicalX(a3, a2));
        assertTrue(lessEqualLexicalX(a1, a1));
        assertFalse(lessEqualLexicalX(b2, b3));
    }

    @Test
    public void testLessThanLexicalY() throws Exception {
        assertTrue(lessThanLexicalY(a1, b1));
        assertTrue(lessThanLexicalY(a3, a2));
        assertFalse(lessThanLexicalY(a1, a1));
        assertFalse(lessThanLexicalY(b2, b3));
    }

    @Test
    public void testLessEqualLexicalY() throws Exception {
        assertTrue(lessEqualLexicalY(a1, b1));
        assertTrue(lessEqualLexicalY(a3, a2));
        assertTrue(lessEqualLexicalY(a1, a1));
        assertFalse(lessEqualLexicalY(b2, b3));
    }

    @Test
    public void testLessThanPolar() throws Exception {
        assertTrue(lessThanPolar(b1, a1));
        assertFalse(lessThanPolar(a3, a2));
        assertFalse(lessThanPolar(a1, a1));
        assertTrue(lessThanPolar(b2, b3));
    }

    @Test
    public void testLessEqualPolar() throws Exception {
        assertTrue(lessEqualPolar(b1, a1));
        assertTrue(lessEqualPolar(a3, a2));
        assertTrue(lessEqualPolar(a1, a1));
        assertTrue(lessEqualPolar(b2, b3));
    }

    @Test(expected = OriginPointException.class)
    public void testLessEqualPolarException() throws Exception {
        lessEqualPolar(zero, a1);
    }

    @Test(expected = OriginPointException.class)
    public void testLessThanPolarException() throws Exception {
        lessThanPolar(zero, a1);
    }
}