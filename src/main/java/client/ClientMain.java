package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 12345);
        PrintWriter scc = new PrintWriter(s.getOutputStream());
        while(true) {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            line += "\n";
            System.out.println(line);
            scc.write(line);
            scc.flush();
            if(line.equals("quit"))
                break;
        }
        s.close();
    }
}
