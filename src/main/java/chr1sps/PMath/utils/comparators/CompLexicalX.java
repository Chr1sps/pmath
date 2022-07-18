package chr1sps.PMath.utils.comparators;

import static chr1sps.PMath.utils.algorithms.compareToLexicalX;

import java.util.Comparator;

import chr1sps.PMath.shapes.Point;

public class CompLexicalX implements Comparator<Point> {
    @Override
    public int compare(Point first, Point other) {
        return compareToLexicalX(first, other);
    }
}
