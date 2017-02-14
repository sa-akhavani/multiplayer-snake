package server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    Socket serverSocket;

    public ServerThread(Socket ss) {
        this.serverSocket = ss;
    }

    public void run() {
        String line;
        try {
           BufferedReader br = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            while (true) {
                if(!br.ready())
                    continue;
                line = br.readLine();
                System.out.println(line);
                if(line.equals("quit"))
                    break;
            }
            serverSocket.close();
        } catch (IOException e) {
        }

    }
}
