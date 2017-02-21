package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by ali on 2/21/17.
 */
public class Transceiver {
    private Socket socket;

    public Transceiver(Socket s) {
        this.socket = s;
    }

    public void send(String message) throws IOException {
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        output.write(message);
        output.flush();
        output.close();
    }

    public String recieve() throws IOException {
        String res = "";
        BufferedReader clientMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String response;
        while ((response = clientMessage.readLine()) != null) {
            res+= response;
            res+= '\n';
            if(!clientMessage.ready())
                break;
        }
        return res;
    }

    public void close() throws IOException {
        socket.close();
    }
}
