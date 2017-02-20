package common;

import java.util.ArrayList;

/**
 * Created by ali on 2/19/17.
 */
public class Snake {
    private ArrayList<Point> joints;

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

    //    public void strech() {
//        if (joint.get(0).getX()) {
//
//        }
//    }
}