// 100,000 accounts of $100 each; two threads each take $80 from every one that has it
public class Withdrawals {
    public static void main(String[] args) throws InterruptedException {
        Account[] accounts = new Account[100_000];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account("a" + i, 100);
        }
        Runnable work = () -> {
            for (Account account : accounts) {
                synchronized (account) {
                if (account.getBalance() >= 80) {
                    account.add(-80);
                }
                }
            }
        };
        Thread first = new Thread(work);
        Thread second = new Thread(work);
        first.start();
        second.start();
        first.join();
        second.join();
        int below = 0;
        for (Account account : accounts) {
            if (account.getBalance() < 0) {
                below++;
            }
        }
        System.out.println(below + " accounts below zero");
    }
}