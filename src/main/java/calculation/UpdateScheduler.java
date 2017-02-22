package calculation;

import board.Board;
import board.Point;
import common.SharedData;
import common.User;

import java.io.IOException;
import java.util.TimerTask;

public class UpdateScheduler extends TimerTask {

    private SharedData sharedData;

    public UpdateScheduler(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run() {
        System.out.println("Message every two seconds!");
        handleActions();
        try {
            sendMaps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMaps() throws IOException {
        Board b = sharedData.getBoard();
        Point p = null;
//        b.randomSnakeGenerator();
        for (User u :
                sharedData.getUsers()) {
            System.out.println(b.rotate(u.getNumber()).getMovings());
            u.send(b.rotate(u.getNumber()).getMovings());
        }
    }

    private void handleActions() {
//        Board b = sharedData.getBoard();
        Point p = null;
        for (User u:
                sharedData.getUsers()) {
            if (u.getAction().getWay() != null) {
//                System.out.println(u.getAction().getWay());
                System.out.println("in!");
                p = u.getSnake().move(u.getAction());
                sharedData.getBoard().addRemoving(p);
//                System.out.println("movings: " + b.getMovings());
            } else {
                System.out.println("empty :|");
            }
//            u.send(b.rotate(u.getNumber()).getMovings());
            sharedData.getBoard().getRemoves().remove(p);
        }
        /*
        TODO: 21/02/2017
            invoke actions,
            move forward,
            update map,
            delete losers,
            if food eaten generate food and stretch snakes,
            check end
         */
    }

    public Board rotateMap(int num) {
        return sharedData.getBoard().rotate(num);
    }
}