// five philosophers, each eating 100,000 times, left fork first, then right
public class Dinner {
    static int[] meals = new int[5];

    static void eat(int who, Fork left, Fork right) {
        Fork first;
        Fork second;

        if (left.number < right.number) {
            first = left;
            second = right;
        } else {
            first = right;
            second = left;
        }

        synchronized (first) {
            synchronized (second) {
                meals[who]++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Fork[] forks = new Fork[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork(i);
        }
        Thread[] philosophers = new Thread[5];
        for (int i = 0; i < 5; i++) {
            int who = i;
            Fork left = forks[i];
            Fork right = forks[(i + 1) % 5];
            philosophers[i] = new Thread(() -> {
                for (int meal = 0; meal < 100_000; meal++) {
                    eat(who, left, right);
                }
            });
        }
        for (Thread p : philosophers) {
            p.start();
        }
        for (Thread p : philosophers) {
            p.join();
        }
        System.out.println(java.util.Arrays.toString(meals));
    }
}