package chr1sps.PMath.utils.comparators;

import static chr1sps.PMath.utils.algorithms.compareToPolar;

import java.util.Comparator;

import chr1sps.PMath.exceptions.OriginPointException;
import chr1sps.PMath.shapes.Point;

public class CompPolar implements Comparator<Point> {
    @Override
    public int compare(Point first, Point other) {
        try {
            return compareToPolar(first, other);
        } catch (OriginPointException e) {
            return -1;
        }
    }
}
