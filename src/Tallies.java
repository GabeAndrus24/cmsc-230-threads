// two threads, each with a Tally of its own, adding to the one total
public class Tallies {
    public static void main(String[] args) throws InterruptedException {
        Tally mine = new Tally();
        Tally yours = new Tally();
        Thread first = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                mine.add();
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                yours.add();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(Tally.total);
    }
}