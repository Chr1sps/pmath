package chr1sps.PMath.utils.comparators;

import static chr1sps.PMath.utils.algorithms.compareToLexicalY;

import java.util.Comparator;

import chr1sps.PMath.shapes.Point;

public class CompLexicalY implements Comparator<Point> {
    @Override
    public int compare(Point first, Point other) {
        return compareToLexicalY(first, other);
    }
}
