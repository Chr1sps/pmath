import org.junit.*;

public class SegmentTests {
    private static Point point_1, point_2, point_2_2, point_3, point_4;

    @BeforeClass
    public static void setUp() {
        point_1 = new Point(1.0, 2.0);
        point_2 = new Point(4.0, -2.0);
        point_2_2 = new Point(point_2);
        point_3 = new Point(0.0, 2.0);
        point_4 = new Point(1.003, 1.996);
    }

    @Test
    public void testConstructorValid() throws IdenticalVerticesException {
        Segment seg = new Segment(point_1, point_2);
        Assert.assertEquals(1.0, seg.getA().getX(), 0.0);
        Assert.assertEquals(2.0, seg.getA().getY(), 0.0);
        Assert.assertEquals(4.0, seg.getB().getX(), 0.0);
        Assert.assertEquals(-2.0, seg.getB().getY(), 0.0);

    }

    @Test(expected = IdenticalVerticesException.class)
    public void testConstructorException() throws IdenticalVerticesException {
        new Segment(point_2, point_2_2);
    }

    @Test
    public void testSettersValid() throws IdenticalVerticesException {
        Segment seg = new Segment(point_1, point_2);
        seg.setB(point_3);
        Assert.assertEquals(0.0, seg.getB().getX(), 0.0);
        Assert.assertEquals(2.0, seg.getB().getY(), 0.0);
    }

    @Test(expected = IdenticalVerticesException.class)
    public void testSettersException() throws IdenticalVerticesException {
        Segment seg = new Segment(point_1, point_2);
        seg.setA(point_2_2);
    }

    @Test
    public void testGetLengthOneDimension() throws IdenticalVerticesException {
        Segment seg = new Segment(point_1, point_3);
        Assert.assertEquals(1.0, seg.getLength(), 0.0 /* 1e-15 */);
    }

    @Test
    public void testGetLengthTwoDimensions() throws IdenticalVerticesException {
        Segment seg = new Segment(point_1, point_2);
        Assert.assertEquals(5.0, seg.getLength(), 0.0 /* 1e-15 */);
    }

    @Test
    public void testGetLengthRounding() throws IdenticalVerticesException {
        Segment seg = new Segment(point_1, point_4);
        Assert.assertEquals(0.005, seg.getLength(), 1e-15);
    }

    @Test
    public void testToString() throws Exception {
        Segment seg = new Segment(point_1, point_2);
        Assert.assertEquals("(1.0000, 2.0000)-(4.0000, -2.0000)", seg.toString());
    }

    @Test
    public void testEquals() throws Exception {
        Segment seg_1 = new Segment(point_1, point_2), seg_2 = new Segment(point_1, point_2),
                seg_3 = new Segment(point_1, point_3);
        Assert.assertTrue(seg_1.equals(seg_2));
        Assert.assertFalse(seg_1.equals(seg_3));
        Assert.assertFalse(seg_2.equals(seg_3));
    }

    @Test
    public void testHashCode() throws Exception {
        Segment seg_1 = new Segment(point_1, point_2), seg_2 = new Segment(point_1, point_2),
                seg_3 = new Segment(point_1, point_3);
        Assert.assertTrue(seg_1.hashCode() == seg_2.hashCode());
        Assert.assertFalse(seg_1.hashCode() == seg_3.hashCode());
        Assert.assertFalse(seg_2.hashCode() == seg_3.hashCode());
    }

    @Test
    public void testClone() throws Exception {
        Segment seg = new Segment(point_1, point_2), cloned = (Segment) seg.clone();
        Assert.assertEquals(seg, cloned);
        Assert.assertFalse(seg == cloned);
    }
}
