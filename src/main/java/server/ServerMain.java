package server;

import calculation.UpdateScheduler;
import common.SharedData;
import common.User;
import userThreads.HeartBeatThread;
import userThreads.UserDataThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.Timer;

public class ServerMain {
    public static Timer updateTimer;

    public static void main(String[] args) throws IOException {
        SharedData sharedData = new SharedData();
        String staticsJson = sharedData.getBoard().getStatics();
            System.out.println(staticsJson);
            ServerSocket myServer = new ServerSocket(12345);
        initiateUpdateScheduler(sharedData);

        int number;
        while(true) {
            number = getNumber(sharedData.getSet());
            if (number == -1) {
                continue;
            }
            Socket serverSocket = myServer.accept();
            HeartBeatThread st = new HeartBeatThread(serverSocket, sharedData);
            st.start();
            Socket userDataSocket = myServer.accept();
            User newUser = new User(userDataSocket, number);
            sharedData.getUsers().add(newUser);
            UserDataThread uds = new UserDataThread(newUser, sharedData);
            uds.start();
        }
    }

    private static int getNumber(Set<Integer> set) {
        if (set.contains(1)) {
            set.remove(1);
            return 1;
        }
        else if (set.contains(2)) {
            set.remove(2);
            return 2;
        }
        else if (set.contains(3)) {
            set.remove(3);
            return 3;
        }
        else if (set.contains(4)) {
            set.remove(4);
            return 4;
        }
        return -1;
    }

    private static void initiateUpdateScheduler(SharedData sharedData) {
        updateTimer = new Timer();
        UpdateScheduler st = new UpdateScheduler(sharedData);
        updateTimer.schedule(st, 0, 2000);
    }
}
