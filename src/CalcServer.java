import java.io.*;
import java.net.*;
import java.util.*;

// answers every line a client sends with "echo: " and the line, one client at a time
public class CalcServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(6230)) {
            System.out.println("waiting on port 6230");
            while (true) {
                Socket client = server.accept();
                System.out.println("a client connected");
                serve(client);
                System.out.println("the client left");
            }
        }
    }

    // talks to one client until it leaves
    static void serve(Socket client) throws IOException {
        try (Scanner in = new Scanner(client.getInputStream());
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                out.println(Calculator.answer(line));
            }
        }
    }
}