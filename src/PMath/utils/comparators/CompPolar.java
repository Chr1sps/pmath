package PMath.utils.comparators;

import java.util.Comparator;

import PMath.exceptions.IdenticalVerticesException;
import PMath.exceptions.OriginPointException;
import PMath.shapes.Point;
import PMath.utils.algorithms;

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
