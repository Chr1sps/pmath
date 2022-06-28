import org.junit.*;

import PMath.shapes.Point;
import PMath.shapes.Segment;

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
        Assert.assertEquals(6.0, PMath.utils.determinant(a2, a1, b1), 0.0);
        Assert.assertEquals(9.0, PMath.utils.determinant(a2, a3, b1), 0.0);
        Assert.assertEquals(-2.0, PMath.utils.determinant(b2, new Segment(b3, a2)), 0.0);
        Assert.assertEquals(0.0, PMath.utils.determinant(a3, new Segment(a1, b1)), 0.0);
    }
}