package server;

import java.util.ArrayList;

/**
 * Created by ali on 2/19/17.
 */
public class Snake {
    private ArrayList<Point> joint;

    public Snake() {
        joint = new ArrayList<Point>();
        joint.add(new Point(0,0));
        joint.add(new Point(1,0));
        joint.add(new Point(2,0));
    }

    public ArrayList<Point> getJoint() {
        return joint;
    }

//    public void strech() {
//        if (joint.get(0).getX()) {
//
//        }
//    }
}