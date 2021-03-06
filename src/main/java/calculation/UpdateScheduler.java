package calculation;

import board.Board;
import board.Point;
import board.Snake;
import common.SharedData;
import common.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimerTask;

public class UpdateScheduler extends TimerTask {

    private SharedData sharedData;

    public UpdateScheduler(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run() {
        if (sharedData.getUsers().size() < 2 && !sharedData.gameStarted) {
            try {
                sendMaps();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
                for (User u :
                        sharedData.getUsers()) {
                    if (!u.isAlive()) {
                        return;
                    }
                }
            sharedData.gameStarted = true;
            try {
                handleActions();
                checkFood();
                checkCollision();
                if (checkWinner())
                    System.exit(0);

                sendMaps();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMaps() throws IOException {
        Board b = sharedData.getBoard();
        Point p = null;
        for (User u :
                sharedData.getUsers()) {
//            System.out.println(b.getMovings());
            u.send(b.rotate(u.getNumber()).getMovings());
        }
        b.getRemoves().clear();
    }

    private void handleActions() throws IOException {
        Board b = sharedData.getBoard();
        Point p;
        for (User u:
                sharedData.getUsers()) {
//            System.out.println(u.getUsername() + " - action: " + u.getAction().getWay());
            p = u.getSnake().move(u.getAction());
            b.addRemoving(p);
        }
    }

    private void checkCollision() throws IOException {
        Board b = sharedData.getBoard();
        ArrayList<Snake> snakes = new ArrayList<Snake>();
        for (int i = 0; i < b.getSnakes().size(); i++) {
            Snake s = new Snake();
            snakes.add(s);
        }
        Collections.copy(snakes, b.getSnakes());
//        // self collision
        for (Snake s :
                snakes) {
            if(s.selfCollision()) {
                bgrSnake(s);
            }
        }
//        // with obstacles
        for (Snake s :
                snakes) {
            Point head = s.getHead();
            for (Point o : b.getObstacles()) {
                if(o.getX() == head.getX() &&  o.getY() == head.getY()) {
                    bgrSnake(s);
                    break;
                }
            }
        }

        // other snakes
        ArrayList<Snake> bgredList = new ArrayList<Snake>();
        for (Snake s:snakes) {
            Point head = s.getHead();
            String username = s.getUser().getUsername();
            for (Snake other:snakes) {
//                System.out.println(other.toString());
                if(username.equals(other.getUser().getUsername()))
                    continue;
                else if(other.contains(head)) {
                    bgredList.add(s);
                    break;
                }
            }
        }
        for(Snake s:bgredList)
            bgrSnake(s);

        // out of map
        for (Snake s :
                snakes) {
            Point head = s.getHead();
            if (head.getX() < 0 || head.getX() >= b.getSize() || head.getY() < 0 || head.getY() >= b.getSize()) {
                bgrSnake(s);
            }
        }
    }

    private void bgrSnake(Snake s) throws IOException {
        Board b = sharedData.getBoard();
        sharedData.getSet().add(s.getUser().getNumber());
        b.removeSnake(s.getUser());
        System.out.println(s.getUser().getUsername() + "collapsed!");
        sharedData.getUsers().remove(s.getUser());
    }

    public void checkFood() {
        for (Snake s: sharedData.getBoard().getSnakes()) {
            if(s.getHead().equals(sharedData.getBoard().getFood())) {
                System.out.println(s.getUser().getUsername() + "ate!");
                stretchOthers(s.getUser());
                sharedData.foodNumbers++;
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
                s.stretch();
        }
    }

    public boolean checkWinner() {
        int minSize = Integer.MAX_VALUE;
        Snake winner = null;

        if (sharedData.getUsers().size() == 1 && sharedData.gameStarted) {
            System.out.println("...And the winner is: " + sharedData.getUsers().get(0).getUsername());
            return true;
        }

        if(sharedData.foodNumbers == 3) {
            for (Snake s:sharedData.getBoard().getSnakes()) {
                if(s.getJoint().size() < minSize)
                    winner = s;
            }

            if(winner != null)
                System.out.println("...And the winner is:" + winner.getUser().getUsername());

            return true;
        }

        return false;
    }

    public Board rotateMap(int num) {
        return sharedData.getBoard().rotate(num);
    }
}