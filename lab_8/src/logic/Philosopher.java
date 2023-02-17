package logic;

public class Philosopher extends Thread {
    private final Object leftFork;
    private final Object rightFork;

    public Philosopher(Object rightFork, Object leftFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void run() {
        try {
            while (true) {
                think();
                waiting();
                synchronized (leftFork) {
                    takeLeftFork();
                    synchronized (rightFork) {
                        takeRightFork();
                        waiting();
                        dropRightFork();
                    }
                    dropLeftFork();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
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
