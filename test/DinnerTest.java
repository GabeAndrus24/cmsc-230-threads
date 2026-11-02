import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

class DinnerTest {

    @Test
    void philosophersFinishEating() {
        Dinner.meals = new int[5];

        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Fork[] forks = new Fork[5];

            for (int i = 0; i < 5; i++) {
                forks[i] = new Fork(i);
            }

            Thread[] philosophers = new Thread[5];

            for (int i = 0; i < 5; i++) {
                int who = i;
                philosophers[i] = new Thread(() -> {
                    for (int meal = 0; meal < 100_000; meal++) {
                        Dinner.eat(who, forks[who], forks[(who + 1) % 5]);
                    }
                });
            }

            for (Thread p : philosophers) {
                p.start();
            }

            for (Thread p : philosophers) {
                p.join();
            }

            System.out.println(java.util.Arrays.toString(Dinner.meals));
        });

        for (int meal : Dinner.meals) {
            assertEquals(100_000, meal);
        }
    }
}