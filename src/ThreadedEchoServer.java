import java.io.*;
import java.net.*;
import java.util.*;

public class ThreadedEchoServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(6230)) {
            while (true) {
                Socket client = server.accept();
                new Thread(() -> serve(client)).start();
            }
        }
    }

    public static void serve(Socket client) {
        try (Socket connection = client;
             Scanner in = new Scanner(connection.getInputStream());
             PrintWriter out = new PrintWriter(connection.getOutputStream(), true)) {

            while (in.hasNextLine()) {
                out.println("echo: " + in.nextLine());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}