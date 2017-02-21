package calculation;

import common.SharedData;
import java.util.TimerTask;

public class UpdateScheduler extends TimerTask {

    private SharedData sharedData;

    public UpdateScheduler(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run() {
        System.out.println("kir every two secends!");
        handleActions();
        sendMaps();

    }

    private void sendMaps() {
        /*
        TODO:
            -generate 4 maps
            -send maps
         */
    }

    private void handleActions() {
        /*
        TODO: 21/02/2017
            invoke actions,
            move forward,
            update map,
            delete losers,
            if food eaten generate food and stretch snakes,
            check end
         */
    }
}