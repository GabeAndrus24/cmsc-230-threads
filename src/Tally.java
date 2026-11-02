// one total for the whole program, added to through any Tally
class Tally {
    static int total;

    void add() {
        synchronized (Tally.class) {
            total++;
        }
    }
}