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

    public void setWay(String way, int num) {
        if(num == 1)
            this.way = way;
        else if(num == 20){
            if(way.equals("up"))
                this.way = "right";
            else if(way.equals("right"))
                this.way = "down";
            else if(way.equals("down"))
                this.way = "left";
            else if(way.equals("left"))
                this.way = "up";
        } else if (num == 3) {
            if(way.equals("up"))
                this.way = "down";
            else if(way.equals("right"))
                this.way = "left";
            else if(way.equals("down"))
                this.way = "up";
            else if(way.equals("left"))
                this.way = "right";
        } else if (num == 4) {
            if(way.equals("up"))
                this.way = "left";
            else if(way.equals("right"))
                this.way = "up";
            else if(way.equals("down"))
                this.way = "right";
            else if(way.equals("left"))
                this.way = "down";
        }
        this.hasNew = true;
    }

    public boolean hasNew() {
        return hasNew;
    }
}