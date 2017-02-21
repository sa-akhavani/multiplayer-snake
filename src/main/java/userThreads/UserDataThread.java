package userThreads;

import board.Board;
import common.User;

import java.io.IOException;

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
        while (true) {

        }
    }

    private void initialSending() {
        try {
            user.send(board.getStatics());
//            System.out.println();
        } catch (IOException e) {
            e.printStackTrace(); // TODO: 2/21/17 close socket
        }
    }
}
