package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket myServer = new ServerSocket(12345);
        while(true) {
            Socket serverSocket = myServer.accept();
            ServerThread ct = new ServerThread(serverSocket);
            ct.start();
        }
    }
}
