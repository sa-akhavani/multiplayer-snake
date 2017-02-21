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
    private String way;
    private Socket userSocket;
    private Snake snake;
    private int number;

    public User(Socket userSocket, int number) {
        this.userSocket = userSocket;
        this.way = "down";
        this.number = number;
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
}