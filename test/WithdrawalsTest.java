import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class WithdrawalsTest {

    @Test
    void noAccountBelowZero() throws InterruptedException {
        Account[] accounts = new Account[100_000];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account("a" + i, 100);
        }

        Runnable work = () -> {
            for (Account account : accounts) {
                account.takeIfEnough(80);
            }
        };

        Thread one = new Thread(work);
        Thread two = new Thread(work);

        one.start();
        two.start();
        one.join();
        two.join();

        for (Account account : accounts) {
            assertTrue(account.getBalance() >= 0);
        }
    }

    @Test
    void takeIfEnoughWorks() {
        Account account = new Account("Alice", 100);

        assertTrue(account.takeIfEnough(80));
        assertFalse(account.takeIfEnough(80));
        assertEquals(20, account.getBalance());
    }
}