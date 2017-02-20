package userThreads;

import board.Board;
import common.User;

public class UserDataThread extends Thread{
    private User user;
    private Board board;

    public UserDataThread(User user, Board board) {
        this.user = user;
        this.board = board;
    }


    @Override
    public void run() {
        initialSending();
    }

    private void initialSending(){

    }
}
