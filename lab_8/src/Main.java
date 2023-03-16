import logic.Philosopher;
import logic.PhilosopherSemaphore;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    private static final int COUNT_PHILOSOPHERS = 5;

    public static void main(String[] args) {
        System.out.println("Выберите способ решения");
        System.out.println("[1] - Лок");
        System.out.println("[2] - Семафоры");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        switch (choose) {
            case 1 -> lockDiningPholosophers();
            case 2 -> semaphoreDiningPholosophers();
        }
    }

    private static void semaphoreDiningPholosophers() {
        Semaphore[] forksSemaphore = new Semaphore[COUNT_PHILOSOPHERS];
        for (int i = 0; i < COUNT_PHILOSOPHERS; i++) {
            forksSemaphore[i] = new Semaphore(1);
        }
        Semaphore meals = new Semaphore(3);
        for (int i = 0; i < COUNT_PHILOSOPHERS; i++) {
            Thread thread = new Thread(new PhilosopherSemaphore(forksSemaphore[i],
                    forksSemaphore[(i + 1) % COUNT_PHILOSOPHERS],
                    meals),
                    String.valueOf(i + 1));
            thread.start();
        }
    }

    private static void lockDiningPholosophers() {
        Object[] forks = new Object[COUNT_PHILOSOPHERS];
        for (int i = 0; i < COUNT_PHILOSOPHERS; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < COUNT_PHILOSOPHERS; i++) {
            Thread thread;
            if (i == COUNT_PHILOSOPHERS - 1) {
                thread = new Thread(new Philosopher(forks[0],
                        forks[i]),
                        String.valueOf(i + 1));
            } else {
                thread = new Thread(new Philosopher(forks[i],
                        forks[i + 1]),
                        String.valueOf(i + 1));
            }
            thread.start();
        }
    }
}
