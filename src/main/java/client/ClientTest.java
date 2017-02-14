package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by ali on 2/14/17.
 */
public class ClientTest {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 12345);
        Socket client2 = new Socket("localhost", 12345);

//        PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
//        output.write("Sag!");
//        output.flush();

        PrintWriter heartBeat = new PrintWriter(client2.getOutputStream());
        heartBeat.write("I am alive!");
        heartBeat.flush();
    }
}
