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

    @AfterClass
    public static void cleanUp() {

    }

    @Test
    public void testConstructorValid() throws Exception {
        Segment seg = new Segment(point_1, point_2);
        Assert.assertEquals(seg.getA().getX(), 1.0, 0.0);
        Assert.assertEquals(seg.getA().getY(), 2.0, 0.0);
        Assert.assertEquals(seg.getB().getX(), 4.0, 0.0);
        Assert.assertEquals(seg.getB().getY(), -2.0, 0.0);

    }

    @Test(expected = Exception.class)
    public void testConstructorException() throws Exception {
        new Segment(point_2, point_2_2);
    }

    @Test
    public void testSettersValid() throws Exception {
        Segment seg = new Segment(point_1, point_2);
        seg.setB(point_3);
        Assert.assertEquals(seg.getB().getX(), 0.0, 0.0);
        Assert.assertEquals(seg.getB().getY(), 2.0, 0.0);
    }

    @Test(expected = Exception.class)
    public void testSettersException() throws Exception {
        Segment seg = new Segment(point_1, point_2);
        seg.setA(point_2_2);
    }

    @Test
    public void testGetLengthOneDimension() throws Exception {
        Segment seg = new Segment(point_1, point_3);
        Assert.assertEquals(seg.getLength(), 1.0, 0.0 /* 1e-15 */);
    }

    @Test
    public void testGetLengthTwoDimensions() throws Exception {
        Segment seg = new Segment(point_1, point_2);
        Assert.assertEquals(seg.getLength(), 5.0, 0.0 /* 1e-15 */);
    }

    @Test
    public void testGetLengthRounding() throws Exception {
        Segment seg = new Segment(point_1, point_4);
        Assert.assertEquals(seg.getLength(), 0.005, 1e-15);
    }
}
