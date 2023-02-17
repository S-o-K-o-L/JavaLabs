import logic.Philosopher;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int COUNT_PHILOSOPHERS = 5;

    public static void main(String[] args) {
        List<Object> forks = new ArrayList<>(COUNT_PHILOSOPHERS);
        for (int i = 0; i < COUNT_PHILOSOPHERS; i++) {
            forks.add(new Object());
        }
        for (int i = 0; i < COUNT_PHILOSOPHERS; i++) {
            Thread thread;
            if (i == COUNT_PHILOSOPHERS - 1) {
                thread = new Thread(new Philosopher(forks.get(0),
                        forks.get(i)),
                        String.valueOf(i + 1));
            } else {
                thread = new Thread(new Philosopher(forks.get(i),
                        forks.get(i + 1)),
                        String.valueOf(i + 1));
            }
            thread.start();
        }

    }
}
