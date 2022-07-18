package chr1sps.PMath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import chr1sps.PMath.exceptions.IdenticalPointsException;
import chr1sps.PMath.shapes.Point;
import chr1sps.PMath.shapes.Segment;

public class SegmentTest {
    private static Point a1, a2, a3, b1, b2, b3, zero, floating;

    @BeforeClass
    public static void setUp() {
        a1 = new Point(1, 2);
        a2 = new Point(0, 4);
        a3 = new Point(0, 1);
        b1 = new Point(3, 4);
        b2 = new Point(4, 3);
        b3 = new Point(2, 3);
        zero = new Point(0, 0);
        floating = new Point(.003, .004);
    }

    @Test
    public void testConstructorValid() throws IdenticalPointsException {
        Segment seg = new Segment(a1, b1);
        assertEquals(1.0, seg.getA().getX(), 0.0);
        assertEquals(2.0, seg.getA().getY(), 0.0);
        assertEquals(3.0, seg.getB().getX(), 0.0);
        assertEquals(4.0, seg.getB().getY(), 0.0);

    }

    @Test(expected = IdenticalPointsException.class)
    public void testConstructorException() throws IdenticalPointsException {
        new Segment(a1, a1);
    }

    @Test
    public void testSettersValid() throws IdenticalPointsException {
        Segment seg = new Segment(a1, b1);
        seg.setB(b2);
        assertEquals(4.0, seg.getB().getX(), 0.0);
        assertEquals(3.0, seg.getB().getY(), 0.0);
    }

    @Test(expected = IdenticalPointsException.class)
    public void testSettersException() throws IdenticalPointsException {
        Segment seg = new Segment(a1, b1);
        seg.setA(b1);
    }

    @Test
    public void testGetLengthOneDimension() throws IdenticalPointsException {
        Segment seg = new Segment(a2, zero);
        assertEquals(4.0, seg.getLength(), 0.0 /* 1e-15 */);
    }

    @Test
    public void testGetLengthTwoDimensions() throws IdenticalPointsException {
        Segment seg = new Segment(zero, b1);
        assertEquals(5.0, seg.getLength(), 0.0 /* 1e-15 */);
    }

    @Test
    public void testGetLengthRounding() throws IdenticalPointsException {
        Segment seg = new Segment(zero, floating);
        assertEquals(0.005, seg.getLength(), 1e-15);
    }

    @Test
    public void testIsAdherentPoint() throws Exception {
        Segment seg = new Segment(a1, b1);
        assertTrue(seg.isAdherent(b3));
        assertTrue(seg.isAdherent(a1));
        assertFalse(seg.isAdherent(zero));
    }

    @Test
    public void testIsAdherentSegment() throws Exception {
        Segment seg = new Segment(a1, b1), seg_2 = new Segment(a3, b1);
        assertTrue(seg.isAdherent(seg));
        assertTrue(seg_2.isAdherent(seg));
        assertTrue(seg_2.isAdherent(new Segment(a1, b3)));
        assertFalse(seg.isAdherent(new Segment(a2, b2)));
        assertFalse(seg.isAdherent(new Segment(a3, b3)));
    }

    @Test
    public void testIsIntersected() throws Exception {
        Segment seg1 = new Segment(a1, b1),
                seg2 = new Segment(a2, b2),
                seg3 = new Segment(a3, b3),
                seg4 = new Segment(a2, b3);
        assertTrue(seg1.isIntersected(seg1));
        assertTrue(seg1.isIntersected(seg2));
        assertTrue(seg1.isIntersected(seg3));
        assertFalse(seg2.isIntersected(seg3));
        assertTrue(seg3.isIntersected(seg4));
        assertTrue(seg1.isIntersected(seg4));
    }

    @Test
    public void testIsColinearPoint() throws Exception {
        Segment seg = new Segment(a1, b1);
        assertTrue(seg.isColinear(b3));
        assertTrue(seg.isColinear(a1));
        assertFalse(seg.isColinear(a2));
        assertFalse(seg.isColinear(zero));
    }

    @Test
    public void testIsColinearSegment() throws Exception {
        Segment seg = new Segment(a1, b1), seg_2 = new Segment(a3, b1);
        assertTrue(seg.isColinear(seg));
        assertTrue(seg_2.isColinear(seg));
        assertTrue(seg_2.isColinear(new Segment(a1, b3)));
        assertFalse(seg.isColinear(new Segment(a2, b2)));
        assertTrue(seg.isColinear(new Segment(a3, b3)));
    }

    @Test
    public void testToString() throws Exception {
        Segment seg = new Segment(a1, b1);
        assertEquals("(1.0000, 2.0000)-(3.0000, 4.0000)", seg.toString());
    }

    @Test
    public void testEquals() throws Exception {
        Segment seg_1 = new Segment(a1, b1), seg_2 = new Segment(a1, b1),
                seg_3 = new Segment(a1, b2), seg_4 = new Segment(b1, a1);
        assertTrue(seg_1.equals(seg_2));
        assertFalse(seg_1.equals(seg_3));
        assertFalse(seg_2.equals(seg_3));
        assertFalse(seg_1.equals(seg_4));

    }

    @Test
    public void testEqualsIgnoreOrder() throws Exception {
        Segment seg_1 = new Segment(a1, b1), seg_2 = new Segment(a1, b1),
                seg_3 = new Segment(a1, b2), seg_4 = new Segment(b1, a1);
        assertTrue(seg_1.equalsIgnoreOrder(seg_2));
        assertFalse(seg_1.equalsIgnoreOrder(seg_3));
        assertFalse(seg_2.equalsIgnoreOrder(seg_3));
        assertTrue(seg_1.equalsIgnoreOrder(seg_4));

    }

    @Test
    public void testHashCode() throws Exception {
        Segment seg_1 = new Segment(a1, b1), seg_2 = new Segment(a1, b1),
                seg_3 = new Segment(a1, b2);
        assertTrue(seg_1.hashCode() == seg_2.hashCode());
        assertFalse(seg_1.hashCode() == seg_3.hashCode());
        assertFalse(seg_2.hashCode() == seg_3.hashCode());
    }

    @Test
    public void testClone() throws Exception {
        Segment seg = new Segment(a1, b1), cloned = (Segment) seg.clone();
        assertEquals(seg, cloned);
        assertFalse(seg == cloned);
    }
}
