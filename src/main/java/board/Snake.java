package board;

import common.User;
import server.Action;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ali on 2/19/17.
 */
public class Snake {
    private ArrayList<Point> joints;
    private Point previousTail;
    private User user;

    Random rand = new Random();

    public Snake() {
        joints = new ArrayList<Point>();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Snake(int num) {
        joints = new ArrayList<Point>();
        if(num == 1) {
            joints.add(new Point(0,0));
            joints.add(new Point(1,0));
            joints.add(new Point(2,0));
        } else if (num == 2) {
            joints.add(new Point(9,0));
            joints.add(new Point(9,1));
            joints.add(new Point(9,2));

        } else if(num == 3) {
            joints.add(new Point(9,9));
            joints.add(new Point(8,9));
            joints.add(new Point(7,9));
        } else if(num == 4) {
            joints.add(new Point(0,9));
            joints.add(new Point(0,8));
            joints.add(new Point(0,7));
        }
    }

    public ArrayList<Point> getJoint() {
        return joints;
    }

    @Override
    public String toString() {
        String output = "[";
        for (Point j: joints) {
            output += j.toString() + ", ";
        }

        output = output.substring(0,output.length()-2);
        output += "]";
        return output;
    }

    //for debugging purposes
    public void randomGenerate() {
        joints.clear();
        for(int i = 0; i < 3; i++) {
            int  x = rand.nextInt(10);
            int  y = rand.nextInt(10);
            Point p = new Point(x, y);
            joints.add(p);
        }
    }
    public Snake rotate(int num, int size) {
        Snake result = new Snake();
        result.getJoint().clear();
        for (Point p:
             joints) {
            result.getJoint().add(p.rotate(num, size));
        }

        return result;
    }

    public Point move(Action action) {      //removes tail and changes head location
        int size = joints.size();
        Point p = joints.get(size-1);
        joints.add(action.movePoint(p));
        this.previousTail = joints.remove(0);
        return previousTail;
    }
        public void stretch() {
            joints.add(0, previousTail);
    }
}