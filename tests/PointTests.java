import org.junit.*;

public class PointTests {
    @BeforeClass
    public static void setUp() {
        // Point point = new Point(1.0, 2.0);
    }

    @AfterClass
    public static void cleanUp() {

    }

    @Test
    public void testConstructor() {
        Point point = new Point(1.0, 2.0);
        Assert.assertEquals(1.0, point.getX(), 0.0);
        Assert.assertEquals(2.0, point.getY(), 0.0);
    }

    @Test
    public void testCopyingConstructor() {
        Point point_1 = new Point(1.0, 2.0), point_2 = new Point(point_1);
        Assert.assertEquals(1.0, point_2.getX(), 0.0);
        Assert.assertEquals(2.0, point_2.getY(), 0.0);
    }

    @Test
    public void testSetters() {
        Point point = new Point(1.0, 2.0);
        point.setX(0.0);
        point.setY(-1.0);
        Assert.assertEquals(0.0, point.getX(), 0.0);
        Assert.assertEquals(-1.0, point.getY(), 0.0);
    }

    @Test
    public void testEquals() {
        Point point_1 = new Point(1.0, 2.0), point_2 = new Point(1.0, 2.0), point_3 = new Point(1.1, 2.1);
        Assert.assertTrue(point_1.equals(point_2));
        Assert.assertFalse(point_1.equals(point_3));
        Assert.assertFalse(point_2.equals(point_3));
    }

    @Test
    public void testHashCode() {
        Point point_1 = new Point(1.0, 2.0), point_2 = new Point(1.0, 2.0), point_3 = new Point(1.1, 2.1);
        int hash_1 = point_1.hashCode(), hash_2 = point_2.hashCode(), hash_3 = point_3.hashCode();
        Assert.assertEquals(hash_1, hash_2);
        Assert.assertNotEquals(hash_1, hash_3);
        Assert.assertNotEquals(hash_2, hash_3);
    }
}
