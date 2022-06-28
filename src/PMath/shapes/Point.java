package PMath.shapes;

public class Point implements Cloneable {
    private double _x, _y;

    public Point(double x, double y) {
        _x = x;
        _y = y;
    }

    public Point(Point other) {
        _x = other._x;
        _y = other._y;
    }

    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public void setX(double x) {
        _x = x;
    }

    public void setY(double y) {
        _y = y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Double temp = _x;
        result = prime * result + temp.hashCode();
        temp = _y;
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
        if (_x != other._x)
            return false;
        if (_y != other._y)
            return false;
        return true;
    }

    public Object clone() throws CloneNotSupportedException {
        Point cloned = (Point) super.clone();
        cloned._x = this._x;
        cloned._y = this._y;
        return cloned;
    }

    public String toString() {
        return String.format("(%.4f, %.4f)", _x, _y);
    }
}
