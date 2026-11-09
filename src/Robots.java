import java.io.*;
import java.net.*;
import java.util.*;

// four robot clients at once, each sending 100,000 lines to CountServer; then
// one more connection asks for the count
public class Robots {
    public static void main(String[] args) throws Exception {
        Thread[] robots = new Thread[4];
        for (int r = 0; r < robots.length; r++) {
            robots[r] = new Thread(() -> {
                try (Socket s = new Socket("localhost", 6230);
                     Scanner in = new Scanner(s.getInputStream());
                     PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
                    for (int i = 0; i < 100_000; i++) {
                        out.println("hello");
                    }
                    out.println("COUNT");
                    in.nextLine();      // the server has read all of this robot's lines
                } catch (IOException e) {
                    System.out.println("a robot failed: " + e.getMessage());
                }
            });
            robots[r].start();
        }
        for (Thread robot : robots) {
            robot.join();
        }
        try (Socket s = new Socket("localhost", 6230);
             Scanner in = new Scanner(s.getInputStream());
             PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
            out.println("COUNT");
            System.out.println("the server counted " + in.nextLine() + " lines of 400000");
        }
    }
}