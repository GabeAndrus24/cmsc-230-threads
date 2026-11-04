import java.io.*;
import java.net.*;
import java.util.*;

// sends each line you type to the server on this machine, and prints its answer
public class EchoClient {
    public static void main(String[] args) throws IOException {
        try (Socket server = new Socket("localhost", 6230);
             Scanner in = new Scanner(server.getInputStream());
             PrintWriter out = new PrintWriter(server.getOutputStream(), true);
             Scanner keyboard = new Scanner(System.in)) {
            System.out.println("connected: type a line, or bye to leave");
            while (keyboard.hasNextLine()) {
                String line = keyboard.nextLine();
                if (line.equals("bye")) {
                    break;
                }
                out.println(line);
                System.out.println(in.nextLine());
            }
        }
    }
}