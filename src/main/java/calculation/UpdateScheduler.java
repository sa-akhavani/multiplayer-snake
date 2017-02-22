package calculation;

import board.Board;
import board.Point;
import board.Snake;
import common.SharedData;
import common.User;

import java.io.IOException;
import java.util.TimerTask;

public class UpdateScheduler extends TimerTask {

    private SharedData sharedData;
    private int foodNumbers = 0;

    public UpdateScheduler(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run() {
        System.out.println("Message every two seconds!");
        try {
            sendMaps();
            handleActions();
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

    public void checkFood() {
        for (Snake s: sharedData.getBoard().getSnakes()) {
            if(s.getJoint().get(0).equals(sharedData.getBoard().getFood())) {
                stretchOthers(s.getUser());
                foodNumbers++;
                sharedData.getBoard().generateNewFood();
                return;
            }
        }
    }

    private void stretchOthers(User user) {
        for (Snake s:sharedData.getBoard().getSnakes()) {
            if(s.getUser().getUsername().equals(user.getUsername()))
                continue;
            else
                user.getSnake().stretch();
        }
    }

    public boolean checkWinner() {
        int minSize = Integer.MAX_VALUE;
        Snake winner = null;
        if(foodNumbers == 15) {
            for (Snake s:sharedData.getBoard().getSnakes()) {
                if(s.getJoint().size() < minSize)
                    winner = s;
            }

            if(winner != null)
                System.out.println("...and the winner is:" + winner.getUser().getUsername());

            return true;
        }

        return false;
    }

    public Board rotateMap(int num) {
        return sharedData.getBoard().rotate(num);
    }
}