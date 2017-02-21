package userThreads;

import board.Board;
import common.User;
import org.json.JSONException;
import org.json.JSONObject;

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
            try {
                readAction();
            } catch (IOException e) {
                break;
                // TODO: 21/02/2017 pls bgr user and close heartbeat socket;
            }
        }
    }

    private void readAction() throws IOException {
        String line = user.recieve();
        String username = "";
        String message = "";
        try {
            JSONObject actionJSON = new JSONObject(line);
            username = actionJSON.getString("username");
            message = actionJSON.getString("message");
            // TODO: 21/02/2017 check username;
            user.getAction().setWay(message);
        } catch (JSONException e) {
            return ;
        }
        System.out.println("got an action! - " + username + '-' + message);
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