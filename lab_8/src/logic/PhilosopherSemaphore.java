package logic;

import java.util.concurrent.Semaphore;

public class PhilosopherSemaphore extends Thread {
    private final Semaphore leftFork;
    private final Semaphore rightFork;
    private final Semaphore meals;

    public PhilosopherSemaphore(Semaphore rightFork, Semaphore leftFork, Semaphore meals) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.meals = meals;
    }

    public void run() {
        while (true) {
            think();
            try {
                waiting();
                meals.acquire();
                leftFork.acquire();
                takeLeftFork();
                rightFork.acquire();
                takeRightFork();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                rightFork.release();
                dropRightFork();
                leftFork.release();
                dropLeftFork();
                meals.release();
            }
        }
    }

    private void waiting() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 5000));
    }

    private void think() {
        System.out.println("Философ "
                + Thread.currentThread().getName() + " думает");
    }

    private void takeLeftFork() {
        System.out.println("Философ "
                + Thread.currentThread().getName() + " взял ЛЕВУЮ вилку");
    }

    private void takeRightFork() {
        System.out.println("Философ "
                + Thread.currentThread().getName()
                + " взял ПРАВУЮ вилку и начал кушать");
    }

    private void dropRightFork() {
        System.out.println("Философ "
                + Thread.currentThread().getName()
                + " перестал кушать и положил ПРАВУЮ вилку");
    }

    private void dropLeftFork() {
        System.out.println("Философ "
                + Thread.currentThread().getName()
                + " положил ЛЕВУЮ вилку и откинулся на спинку кресла");
    }
}

