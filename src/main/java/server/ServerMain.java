package server;

import board.Board;
import board.Point;
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
        Set<Integer> numbers = new HashSet<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

        Board board = new Board();
        Point foodDims = new Point(2, 3);
        board.parseMap("./resources/map.txt", foodDims);
        String staticsJson = board.getStatics();
        System.out.println(staticsJson);
        ServerSocket myServer = new ServerSocket(12345);

        while(true) {
            Socket serverSocket = myServer.accept();
            HeartBeatThread st = new HeartBeatThread(serverSocket);
            st.start();
            Socket userDataSocket = myServer.accept();
            UserDataThread uds = new UserDataThread(new User(userDataSocket), board);
            uds.start();
        }
    }
}
