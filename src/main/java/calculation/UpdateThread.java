package calculation;

import board.Board;
import common.User;

import java.util.ArrayList;
import java.util.Set;

public class UpdateThread extends Thread{
    private ArrayList<User> users;
    private Set<Integer> set;
    private Board board;
    public UpdateThread(ArrayList<User> users, Set<Integer> set, Board board) {
        this.users = users;
        this.set = set;
        this.board = board;
    }

    @Override
    public void run() {

    }
}