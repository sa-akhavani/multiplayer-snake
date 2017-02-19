package server;

import org.json.JSONObject;

/**
 * Created by ali on 2/19/17.
 */
public class User {
    private String username;
    public boolean alive;

    public void setUsername(String username) {
        this.username = username;
    }

    public User() {
        this.alive = false;
    }

    public User(String username) {
        this.username = username;
        this.alive = false;
    }
}
