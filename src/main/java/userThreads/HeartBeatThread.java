package userThreads;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.concurrent.TimeoutException;

public class HeartBeatThread extends Thread{
    private Socket serverSocket;
    private String username;

    public HeartBeatThread(Socket ss) {
        this.serverSocket = ss;
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
