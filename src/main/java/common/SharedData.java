package common;

import board.Board;
import board.Point;
import board.Snake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by MNoroozi on 21/02/2017.
 */
public class SharedData {
    private ArrayList<User> users;
    private Set<Integer> set;
    private int num;
    private Board board;
    public int foodNumbers = 0;
    public boolean gameStarted = false;

    public SharedData() throws IOException {
        this.users = new ArrayList<User>();
        this.set = new HashSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        num = 0;
        board = new Board();
        Point foodDims = new Point(2, 3);
        board.parseMap("./resources/map.txt", foodDims);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public Set<Integer> getSet() {
        return set;
    }

    public void setSet(Set<Integer> set) {
        this.set = set;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
