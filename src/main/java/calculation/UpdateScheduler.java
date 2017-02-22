package calculation;

import board.Board;
import board.Snake;
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
        System.out.println("Message every two secends!");
//        handleActions();
        try {
            sendMaps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMaps() throws IOException {
        Board b = sharedData.getBoard();
//        b.randomSnakeGenerator();
        for (User u:
             sharedData.getUsers()) {
            System.out.println(b.getSnakes());
            System.out.println(b.getMovings());
            u.send(b.rotate(u.getNumber()).getMovings());
        }
    }

    private void handleActions() {
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