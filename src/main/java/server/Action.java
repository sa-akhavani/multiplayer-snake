package server;

/**
 * Created by ali on 2/20/17.
 */
public class Action {
    private String way;
    private boolean hasNew;

    public Action() {
        hasNew = false;
    }

    public String getWay() {
        this.hasNew = false;
        return way;
    }

    public void setWay(String way) {
        this.way = way;
        this.hasNew = true;
    }

    public boolean hasNew() {
        return hasNew;
    }
}