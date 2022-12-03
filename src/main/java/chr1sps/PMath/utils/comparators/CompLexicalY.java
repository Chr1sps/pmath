package chr1sps.PMath.utils.comparators;

import java.util.Comparator;

import chr1sps.PMath.shapes.Point;
import chr1sps.PMath.utils.algorithms;

public class CompLexicalY implements Comparator<Point> {
    @Override
    public int compare(Point first, Point other) {
        return algorithms.compareToLexicalY(first, other);
    }
}
