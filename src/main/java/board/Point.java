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
            return new Point(size-x, y);
        else if(num == 3)
            return new Point(size-x, size-y);
        else if(num == 4)
            return new Point(x, size-y);

        else return null; // TODO: 22/02/2017 exceprion
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
