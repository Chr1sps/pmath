# PMath - planar math package for Java

This project contains source code of PMath package used for basic planar math calculation.

## Structure

Pmath contains following subpackages:

- **PMath.shapes.\*** - all shapes classes (Point, Segment, Polygon)
- **PMath.exceptions.\*** - custom exceptions used in shapes classes
- **PMath.utils.algorithms** - class containing miscellanous planar math algorithms:
  - **determinant** - calculates a determinant of three points / a point and a segment
  - **crossProduct** - calculates a cross product of two points
  - **comparison algorithms** - lexical, polar comparators for points
  (lessThan..(), lessEqual..(), compareTo..()).
- **PMath.utils.comparators.\*** - various classes implementing the Comparator
interface, utilizing the compareTo..() methods from **PMath.utils.algorithms**.
