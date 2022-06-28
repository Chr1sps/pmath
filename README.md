## PMath - planar math package for Java.

This project contains source code of PMath package used for basic planar math calculation.

### Structure

Pmath contains following subpackages:
* PMath.shapes.* - all shapes classes (Point, Segment, Polygon)
* PMath.exceptions.* - custom exceptions used in shapes classes
* PMath.utils - class containing miscellanous planar math algorithms:
  * determinant - calculates a determinant of three points / a point and a segment
  * isAdherent - checks if a point is adherent to a segment
  * areIntersected - checks if two segments are intersected.