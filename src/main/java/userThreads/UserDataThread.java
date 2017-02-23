package userThreads;

import board.Snake;
import common.SharedData;
import common.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserDataThread extends Thread{
    private User user;
    private SharedData sharedData;

    public UserDataThread(User user, SharedData sharedData) {
        this.user = user;
        this.sharedData = sharedData;
    }


    @Override
    public void run() {
        initialSending();
        Snake s = new Snake(user.getNumber());
        sharedData.getBoard().addSnake(s);
        user.addSnake(s);
        s.setUser(user);
        user.getAction().setWay("down", user.getNumber());
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(user.getUserSocket().getInputStream()));
            while (true) {
                readAction(br, line);
            }
        } catch (IOException e) {
            // TODO: 21/02/2017 pls bgr user and close heartbeat socket;
//            e.printStackTrace();
            System.out.println("nothing could be read");
        }
    }

    private void readAction(BufferedReader br, String line) throws IOException {
        line = user.receive(br);
        if (line == null)
            return; // TODO: 2/21/17 Maybe we need to bgr here
//        System.out.println("here: " + line);
        String username = "";
        String message = "";

        try {
            JSONObject actionJSON = new JSONObject(line);
            username = actionJSON.getString("username");
            message = actionJSON.getString("action");

            if (this.user.getUsername() == null)
                this.user.setUsername(username);
            user.getAction().setWay(message, user.getNumber());
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    private void initialSending() {
        try {
            user.send(sharedData.getBoard().rotateStatics(user.getNumber()).getStatics());
        } catch (IOException e) {
            e.printStackTrace(); // TODO: 2/21/17 close socket
        }
    }
}