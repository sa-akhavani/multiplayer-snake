package board;

/**
 * Created by ali on 2/19/17.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point rotate(int num, int size) {
        if(num == 1)
            return new Point(x, y);
        else if (num == 2)
            return new Point(y, -x+size-1);
        else if(num == 3)
            return new Point(size-1-x, size-y-1);
        else if(num == 4)
            return new Point(size-y-1, x);

        else return null; // TODO: 22/02/2017 exceprion
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;

    }
}
