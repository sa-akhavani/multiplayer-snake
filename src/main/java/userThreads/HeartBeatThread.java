package userThreads;

import common.SharedData;
import common.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class HeartBeatThread extends Thread{
    private Socket serverSocket;
    private String username;
    private SharedData sharedData;

    public HeartBeatThread(Socket ss, SharedData sharedData) {
        this.serverSocket = ss;
        this.sharedData = sharedData;
    }

    public void run() {
        String message;
        String line;
        while (true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                while ((line = br.readLine()) != null) {
                    System.out.println("line: " + line);
                    parseHeartBeat(line);
                }

                // TODO: 2/21/17 Add user's number to set!
                for (User u :
                        sharedData.getUsers()) {
                    if (this.username == u.getUsername())
                        sharedData.getSet().add(u.getNumber());
                }

                System.out.println(this.username + " BGRed!");
                serverSocket.close();
                break;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void parseHeartBeat(String line) throws JSONException {
        JSONObject heartBeat = new JSONObject(line);
        String message = "";
        this.username = heartBeat.getString("username");
        message = heartBeat.getString("message");
    }
}
