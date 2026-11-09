import java.io.*;
import java.net.*;
import java.util.*;

// NAME gives this client a name, and HELLO greets it, each client on a thread
// of its own; but the name is one field for every client
public class SharedName {
    static String name;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(6230)) {
            System.out.println("waiting on port 6230");
            while (true) {
                Socket client = server.accept();
                new Thread(() -> serve(client)).start();
            }
        }
    }

    // answers one client's lines until it leaves
    static void serve(Socket client) {
        try (Scanner in = new Scanner(client.getInputStream());
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            while (in.hasNextLine()) {
                String[] words = in.nextLine().split(" ");
                if (words.length == 2 && words[0].equals("NAME")) {
                    name = words[1];
                    out.println("OK");
                } else if (words.length == 1 && words[0].equals("HELLO")) {
                    out.println(name == null ? "ERROR no name yet" : "hello, " + name);
                } else {
                    out.println("ERROR no such command");
                }
            }
        } catch (IOException e) {
            System.out.println("a client failed: " + e.getMessage());
        }
    }
}