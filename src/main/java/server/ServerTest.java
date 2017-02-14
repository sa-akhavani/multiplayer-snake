package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ali on 2/14/17.
 */
public class ServerTest {
    public static void main(String[] args) throws IOException {
        ServerSocket myServer = new ServerSocket(12345);
        Socket serverSocket = myServer.accept();

        String result = "";
        BufferedReader input = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        String line;
        while((line = input.readLine()) != null) {
            result += line + '\n';
            if (!input.ready())
                break;
        }

        System.out.println(result);
//        System.out.println("sag!");
    }
}
