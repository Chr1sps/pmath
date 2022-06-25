public class Point {
    private double x, y;

    Point(double a, double b) {
        x = a;
        y = b;
    }

    Point(Point other) {
        x = other.x;
        y = other.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double a) {
        x = a;
    }

    public void setY(double a) {
        y = a;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Double temp = x;
        result = prime * result + temp.hashCode();
        temp = y;
        result = prime * result + temp.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

}
