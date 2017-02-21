package userThreads;

import board.Board;
import common.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(user.getUserSocket().getInputStream()));
            while (true) {
                readAction(br, line);
            }
        } catch (IOException e) {
            // TODO: 21/02/2017 pls bgr user and close heartbeat socket;
            e.printStackTrace();
        }
    }

    private void readAction(BufferedReader br, String line) throws IOException {
        line = user.recieve(br);
        if (line == null)
            return; // TODO: 2/21/17 Maybe we need to bgr here
        System.out.println("here: " + line);
        String username = "";
        String message = "";

        try {
            JSONObject actionJSON = new JSONObject(line);
            username = actionJSON.getString("username");
            message = actionJSON.getString("message");
            // TODO: 21/02/2017 check username;
            user.getAction().setWay(message);
        } catch (JSONException e) {
            return;
        }
        System.out.println("got an action! - " + username + '-' + message);
    }

    private void initialSending() {
        try {
            user.send(board.getStatics());
        } catch (IOException e) {
            e.printStackTrace(); // TODO: 2/21/17 close socket
        }
    }
}