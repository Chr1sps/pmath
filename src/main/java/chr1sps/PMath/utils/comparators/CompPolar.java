package chr1sps.PMath.utils.comparators;

import java.util.Comparator;

import chr1sps.PMath.exceptions.OriginPointException;
import chr1sps.PMath.shapes.Point;
import chr1sps.PMath.utils.algorithms;

public class CompPolar implements Comparator<Point> {
    @Override
    public int compare(Point first, Point other) {
        try {
            return algorithms.compareToPolar(first, other);
        } catch (OriginPointException e) {
            return -1;
        }
    }
}
