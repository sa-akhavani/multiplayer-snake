package userThreads;

import common.SharedData;
import common.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class HeartBeatThread extends Thread{
    private Socket serverSocket;
    private SharedData sharedData;
    private User user;

    public HeartBeatThread(User newUser, Socket ss, SharedData sharedData) {
        this.user = newUser;
        this.serverSocket = ss;
        this.sharedData = sharedData;
    }

    public void run() {
        String line;
        while (true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                while ((line = br.readLine()) != null) {
                    System.out.println("line: " + line);
                    if (!parseHeartBeat(line))
                        break;
                }

                System.out.println(sharedData.getSet());

                for (User u :
                        sharedData.getUsers()) {
                    if (user.getUsername().equals(u.getUsername())) {
                        System.out.println(user.getUsername() + " BGRed!");
                        sharedData.getSet().add(u.getNumber());
                        System.out.println(sharedData.getSet());
                        sharedData.getBoard().removeSnake(u);
                        sharedData.getUsers().remove(u);
                        break;
                    }
                }
                serverSocket.close();
                break;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean parseHeartBeat(String line) throws JSONException {
        JSONObject heartBeat = new JSONObject(line);
        String username = heartBeat.getString("username");
        String message = heartBeat.getString("message");
        if (!message.equals("HeartBeat"))
            return false;
        this.user.setUsername(username);
        return true;
    }
}
