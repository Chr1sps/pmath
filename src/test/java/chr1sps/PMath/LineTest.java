package chr1sps.PMath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chr1sps.PMath.shapes.Line;
import chr1sps.PMath.shapes.Point;

public class LineTest {
    private static Line line_1, line_2, line_3, line_4, line_5;

    @Before
    public void setUp() throws Exception {
        line_1 = new Line(1, 1, 1);
        line_2 = new Line(2, 2, 2);
        line_3 = new Line(1, 1, 0);
        line_4 = new Line(0, 1, 0);
        line_5 = new Line(3, -1, -2);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("[1.0000x + 1.0000y + 1.0000 = 0]", new Line(1, 1, 1).toString());
        assertEquals("[1.0000x - 1.0000y - 1.0000 = 0]", new Line(1, -1, -1).toString());
        assertEquals("[1.0000x + 1.0000 = 0]", new Line(1, 0, 1).toString());
        assertEquals("[1.0000y + 1.0000 = 0]", new Line(0, 1, 1).toString());
        assertEquals("[1.0000x = 0]", new Line(1, 0, 0).toString());
        assertEquals("[1.0000y = 0]", new Line(0, 1, 0).toString());
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(line_1, line_2);
        assertEquals(line_1, line_1);
        assertNotEquals(line_1, line_3);
    }

    @Test
    public void testClone() throws Exception {
        Line cloned = (Line) line_1.clone();
        assertEquals(line_1, cloned);
        assertEquals("[1.0000x + 1.0000y + 1.0000 = 0]", cloned.toString());
    }

    @Test
    public void testHashCode() throws Exception {
        line_4 = new Line(-1, -1, 0);
        assertEquals(line_1.hashCode(), line_2.hashCode());
        assertEquals(line_1.hashCode(), line_1.hashCode());
        assertNotEquals(line_1.hashCode(), line_3.hashCode());
        assertEquals(line_3.hashCode(), line_4.hashCode());
    }

    @Test
    public void testIsAdherentPoint() throws Exception {
        boolean[][] expected = { { true, false, true, false },
                { false, false, false, true },
                { false, false, false, true },
                { false, true, true, false } };
        Point[] points = { new Point(-1, 0), new Point(2, 4), new Point(1, 1), new Point(0, 0) };
        Line[] lines = { line_1, line_3, line_4, line_5 };
        for (int p = 0; p < 4; ++p) {
            for (int l = 0; l < 4; ++l) {
                assertTrue(lines[l].isAdherent(points[p]) == expected[p][l]);
            }
        }
    }
}
