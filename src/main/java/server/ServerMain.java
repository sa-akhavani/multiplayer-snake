package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {
    ArrayList<User> users;
    public static void main(String[] args) throws IOException {
        ServerSocket myServer = new ServerSocket(12345);
        while(true) {
            Socket serverSocket = myServer.accept();
            ServerThread st = new ServerThread(serverSocket);
            st.start();
        }
    }
}
