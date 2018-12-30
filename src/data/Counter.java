package data;

public class Counter {
    private int count;

    public Counter() {
        count = 0;
    }

    public void addVote() {
        count += 1;
    }

    public int getVotes() {
        return count;
    }
}
