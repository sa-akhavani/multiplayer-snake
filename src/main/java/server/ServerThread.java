package server;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    Socket serverSocket;
    User user;

    public ServerThread(Socket ss) {
        this.serverSocket = ss;
        user = new User();
    }

    public void run() {
        String line;
        String username;
        String message;
        try {
           BufferedReader br = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            while (true) {
                if(!br.ready())
                    continue;
                line = br.readLine();
                System.out.println(line);

                JSONObject heartBeat = new JSONObject(line);
                username = "";
                message = "";
                try {
                    username = heartBeat.getString("username");
                    message = heartBeat.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("logged in! - " + username + '-' + message);

                if(line.equals("quit"))
                    break;
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
