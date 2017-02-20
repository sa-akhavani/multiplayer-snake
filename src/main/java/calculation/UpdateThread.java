package calculation;

import common.User;

import java.util.ArrayList;

public class UpdateThread extends Thread{
    private ArrayList<User> users;


    public UpdateThread(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public void run() {

    }
}
