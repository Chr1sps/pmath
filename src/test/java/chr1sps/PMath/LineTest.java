package chr1sps.PMath;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import chr1sps.PMath.shapes.Line;

public class LineTest {
    @BeforeClass
    public static void setUp() throws Exception {

    }

    @Test
    public void testToString() throws Exception {
        Line line_1 = new Line(1, 1, 1);
        assertEquals("[1.0000x + 1.0000y + 1.0000 = 0]", line_1.toString());
    }
}
