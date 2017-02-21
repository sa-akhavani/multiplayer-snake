package board;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ali on 2/19/17.
 */
public class Snake {
    private ArrayList<Point> joints;
    Random rand = new Random();

    public Snake() {
        joints = new ArrayList<Point>();
        joints.add(new Point(0,0));
        joints.add(new Point(1,0));
        joints.add(new Point(2,0));
    }

    public ArrayList<Point> getJoint() {
        return joints;
    }

    @Override
    public String toString() {
        String output = "[";
        for (Point j: joints) {
            output += j.toString() + ",";
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

    //    public void strech() {
//        if (joint.get(0).getX()) {
//
//        }
//    }
}