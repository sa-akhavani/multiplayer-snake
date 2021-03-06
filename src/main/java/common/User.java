package common;

import board.Snake;
import org.json.JSONObject;
import server.Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by ali on 2/19/17.
 */
public class User {
    private String username;
    private Action action;
    private Socket userSocket;
    private boolean alive;
    private Snake snake;
    private int number;

    public Socket getHeartbeatSocket() {
        return heartbeatSocket;
    }

    private Socket heartbeatSocket;

    public Snake getSnake() {
        return snake;
    }

    public boolean isAlive() {
        return alive;
    }

    public User(int number) {
//        this.way = "down";
        this.number = number;
        this.action = new Action();
    }

    public User(Socket userSocket, int number) {
        this.userSocket = userSocket;
//        this.way = "down";
        this.number = number;
    }

    public void setUserSocket(Socket userSocket) {
        this.userSocket = userSocket;
    }

    public void setHeartbeatSocket(Socket heartbeatSocket) {
        this.heartbeatSocket = heartbeatSocket;
    }

    public Socket getUserSocket() {
        return userSocket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void send(String message) throws IOException {
        PrintWriter output = new PrintWriter(userSocket.getOutputStream());
        output.write(message);
        output.flush();
    }
    public String receive(BufferedReader br) throws IOException {
        String line;
        line = br.readLine();
        return line;
    }

    public void addSnake(Snake s) {
        this.snake = s;
    }

    public void bgr() throws IOException {
        heartbeatSocket.close();
        userSocket.close();
    }

    public void setAlive() {
        this.alive = true;
    }
}