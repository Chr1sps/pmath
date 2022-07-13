import org.junit.*;

import PMath.shapes.Point;
import PMath.shapes.Segment;
import PMath.utils;
import PMath.exceptions.OriginPointException;

public class UtilsTests {
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
        Assert.assertEquals(6.0, utils.determinant(a2, a1, b1), 0.0);
        Assert.assertEquals(9.0, utils.determinant(a2, a3, b1), 0.0);
        Assert.assertEquals(-2.0, utils.determinant(b2, new Segment(b3, a2)), 0.0);
        Assert.assertEquals(0.0, utils.determinant(a3, new Segment(a1, b1)), 0.0);
    }

    @Test
    public void testCrossProduct() throws Exception {
        Assert.assertEquals(11, utils.crossProduct(a1, b1), 0);
        Assert.assertEquals(4, utils.crossProduct(a2, a3), 0);
        Assert.assertEquals(3, utils.crossProduct(b2, a3), 0);

    }

    @Test
    public void testLessThanLexicalX() throws Exception {
        Assert.assertTrue(utils.lessThanLexicalX(a1, b1));
        Assert.assertTrue(utils.lessThanLexicalX(a3, a2));
        Assert.assertFalse(utils.lessThanLexicalX(a1, a1));
        Assert.assertFalse(utils.lessThanLexicalX(b2, b3));
    }

    @Test
    public void testLessEqualLexicalX() throws Exception {
        Assert.assertTrue(utils.lessEqualLexicalX(a1, b1));
        Assert.assertTrue(utils.lessEqualLexicalX(a3, a2));
        Assert.assertTrue(utils.lessEqualLexicalX(a1, a1));
        Assert.assertFalse(utils.lessEqualLexicalX(b2, b3));
    }

    @Test
    public void testLessThanLexicalY() throws Exception {
        Assert.assertTrue(utils.lessThanLexicalY(a1, b1));
        Assert.assertTrue(utils.lessThanLexicalY(a3, a2));
        Assert.assertFalse(utils.lessThanLexicalY(a1, a1));
        Assert.assertFalse(utils.lessThanLexicalY(b2, b3));
    }

    @Test
    public void testLessEqualLexicalY() throws Exception {
        Assert.assertTrue(utils.lessEqualLexicalY(a1, b1));
        Assert.assertTrue(utils.lessEqualLexicalY(a3, a2));
        Assert.assertTrue(utils.lessEqualLexicalY(a1, a1));
        Assert.assertFalse(utils.lessEqualLexicalY(b2, b3));
    }

    @Test
    public void testLessThanPolar() throws Exception {
        Assert.assertTrue(utils.lessThanPolar(b1, a1));
        Assert.assertFalse(utils.lessThanPolar(a3, a2));
        Assert.assertFalse(utils.lessThanPolar(a1, a1));
        Assert.assertTrue(utils.lessThanPolar(b2, b3));
    }

    @Test
    public void testLessEqualPolar() throws Exception {
        Assert.assertTrue(utils.lessEqualPolar(b1, a1));
        Assert.assertTrue(utils.lessEqualPolar(a3, a2));
        Assert.assertTrue(utils.lessEqualPolar(a1, a1));
        Assert.assertTrue(utils.lessEqualPolar(b2, b3));
    }

    @Test(expected = OriginPointException.class)
    public void testLessEqualPolarException() throws Exception {
        utils.lessEqualPolar(zero, a1);
    }

    @Test(expected = OriginPointException.class)
    public void testLessThanPolarException() throws Exception {
        utils.lessThanPolar(zero, a1);
    }
}