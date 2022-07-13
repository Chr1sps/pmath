package PMath.utils.comparators;

import java.util.Comparator;

import PMath.shapes.Point;
import PMath.utils.algorithms;

public class CompLexicalX implements Comparator<Point> {
    @Override
    public int compare(Point first, Point other) {
        return algorithms.compareToLexicalX(first, other);
    }
}
