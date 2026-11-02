import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TallyTest {

    @Test
    void fourThreads() throws InterruptedException {
        Tally.total = 0;
        Tally[] tallies = new Tally[4];

        for (int i = 0; i < tallies.length; i++) {
            tallies[i] = new Tally();
        }

        Thread[] threads = new Thread[4];

        for (int i = 0; i < threads.length; i++) {
            Tally tally = tallies[i];

            threads[i] = new Thread(() -> {
                for (int j = 0; j < 25_000; j++) {
                    tally.add();
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(100_000, Tally.total);
    }
}