package common;

/**
 * Created by ali on 2/20/17.
 */
public class HeartBeat {
    private String username;
    private String message;

    public HeartBeat(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
