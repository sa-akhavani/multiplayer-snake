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
        for (User u :
                sharedData.getUsers()) {
            System.out.println(b.getMovings());
            u.send(b.rotate(u.getNumber()).getMovings());
        }
        b.getRemoves().clear();
    }

    private void handleActions() {
        Board b = sharedData.getBoard();
        Point p;
        for (User u:
                sharedData.getUsers()) {
            if (u.getAction().getWay() != null) {
                System.out.println(u.getUsername() + " - action: " + u.getAction().getWay());
                p = u.getSnake().move(u.getAction());
                b.addRemoving(p);
            } else {
                System.out.println("empty :|");
            }
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