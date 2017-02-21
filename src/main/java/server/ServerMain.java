package server;

import board.Board;
import board.Point;
import calculation.UpdateThread;
import common.User;
import userThreads.HeartBeatThread;
import userThreads.UserDataThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ArrayList<User> onlineUsers = new ArrayList<User>();
        Set<Integer> set = new HashSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);

        Board board = new Board();
        Point foodDims = new Point(2, 3);
        board.parseMap("./resources/map.txt", foodDims);
        String staticsJson = board.getStatics();
        System.out.println(staticsJson);
        ServerSocket myServer = new ServerSocket(12345);

        UpdateThread ut = new UpdateThread(onlineUsers, set, board);
        ut.start();

        int number;
        while(true) {
            number = getNumber(set);
            if (number == -1) {
                continue;
            }
            Socket serverSocket = myServer.accept();
            HeartBeatThread st = new HeartBeatThread(serverSocket);
            st.start();
            Socket userDataSocket = myServer.accept();
            UserDataThread uds = new UserDataThread(new User(userDataSocket, number), board);
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
}
