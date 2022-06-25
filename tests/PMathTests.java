import org.junit.*;

public class PMathTests {
    private static Point a1, a2, a3, b1, b2, b3;

    @BeforeClass
    public static void setUp() {
        a1 = new Point(1, 2);
        a2 = new Point(0, 4);
        a3 = new Point(0, 1);
        b1 = new Point(3, 4);
        b2 = new Point(4, 3);
        b3 = new Point(2, 3);
    }

    @Test
    public void testDeterminant() throws Exception {
        Assert.assertEquals(6.0, PMath.determinant(a2, a1, b1), 0.0);
        Assert.assertEquals(9.0, PMath.determinant(a2, a3, b1), 0.0);
        Assert.assertEquals(-2.0, PMath.determinant(b2, new Segment(b3, a2)), 0.0);
        Assert.assertEquals(0.0, PMath.determinant(a3, new Segment(a1, b1)), 0.0);
    }

    @Test
    public void testAdherence() throws Exception {
        Segment seg = new Segment(a1, b1);
        Assert.assertTrue(PMath.isAdherent(b3, seg));
        Assert.assertFalse(PMath.isAdherent(a3, seg));
        Assert.assertTrue(PMath.isAdherent(a1, seg));
    }

    @Test
    public void testIntersecting() throws Exception {
        Segment seg1 = new Segment(a1, b1),
                seg2 = new Segment(a2, b2),
                seg3 = new Segment(a3, b3),
                seg4 = new Segment(a2, b3);
        Assert.assertTrue(PMath.areIntersected(seg1, seg2));
        Assert.assertTrue(PMath.areIntersected(seg1, seg3));
        Assert.assertFalse(PMath.areIntersected(seg2, seg3));
        Assert.assertTrue(PMath.areIntersected(seg3, seg4));
        Assert.assertTrue(PMath.areIntersected(seg1, seg4));
    }
}