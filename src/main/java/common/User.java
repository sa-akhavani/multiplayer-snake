package common;

import org.json.JSONObject;
import server.Action;

import java.net.Socket;

/**
 * Created by ali on 2/19/17.
 */
public class User {
    private String username;
    private Action action;
    private Socket userSocket;
    private int number;

    public User(Socket userSocket) {
        this.userSocket = userSocket;
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

    public Socket getUserSocket() {
        return userSocket;
    }

    public void setUserSocket(Socket userSocket) {
        this.userSocket = userSocket;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
