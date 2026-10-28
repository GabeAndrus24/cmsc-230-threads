import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

// section 4.2: your test goes here
class TransfersTest {

    @Test
    void noDeadlock() {
        Account alice = new Account("Alice", 1000);
        Account bob = new Account("Bob", 1000);

        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Thread one = new Thread(() -> {
                for (int i = 0; i < 100_000; i++) {
                    Transfers.transfer(alice, bob, 1);
                }
            });
            Thread two = new Thread(() -> {
                for (int i = 0; i < 100_000; i++) {
                    Transfers.transfer(bob, alice, 1);
                }
            });

            one.start();
            two.start();
            one.join();
            two.join();
        });
        assertEquals(2000, alice.getBalance() + bob.getBalance());
    }
}
