package server;

import common.Board;
import common.Point;
import common.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ArrayList<User> onlineUsers;

        Board board = new Board();
        Point food = new Point(2, 3);
        board.parseMap("./resources/map.txt", food);
        String staticsJson = board.getStatics();
        System.out.println(staticsJson);
        ServerSocket myServer = new ServerSocket(12345);

        while(true) {
            Socket serverSocket = myServer.accept();
            ServerThread st = new ServerThread(serverSocket);
            st.start();
        }
    }
}
