package common;

import org.json.JSONObject;
import server.Action;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by ali on 2/19/17.
 */
public class User {
    private String username;
    private Action action;
    private Transceiver transceiver;
//    private Socket userSocket;
    private int number;

    public User(Socket userSocket) {
        this.transceiver = new Transceiver(userSocket);
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
        transceiver.send(message);
    }
    public String recieve() throws IOException {
        return transceiver.recieve();
    }
}
