package userThreads;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.concurrent.TimeoutException;

public class HeartBeatThread extends Thread{
    private Socket serverSocket;
    private Timer timer;
    private boolean alive;
    boolean bgr;
//    String username;  /todo

    public HeartBeatThread(Socket ss) {
        this.serverSocket = ss;
        timer = new Timer();
        alive = true;
    }
    public void run() {
        String line;
        String username;
        String message;
        while (true) {
            alive = false;
            new Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            checkAlive();
                        }
                    },
                    0, 5000
            );
            if (bgr)
                break;

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                while (true) {
                    if(!br.ready())
                        continue;
                    line = br.readLine();
                    System.out.println(line);

                    alive = checkHeartBeat(line);
                    if(line.equals("quit"))
                        break;
                }
                serverSocket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (JSONException e) {
                e.printStackTrace();
            }



            //get heartbeat
            //check heartbeat and throw exception
        }

    }

    public boolean checkHeartBeat(String line) throws JSONException {

        JSONObject heartBeat = new JSONObject(line);
        String username = "";
        String message = "";
        try {
            username = heartBeat.getString("username");
            message = heartBeat.getString("message");
        } catch (JSONException e) {
            return false;
        }
        System.out.println("logged in! - " + username + '-' + message);
        return true;
    }

    private void checkAlive() {
        System.out.println("timer!");
        if (!this.alive) {
            System.out.println("kir");
            this.destroy();
            System.out.println("ok");
//            bgr = true;
        }
    }
}
