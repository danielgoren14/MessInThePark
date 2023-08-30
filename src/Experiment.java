import java.util.ArrayList;
import java.util.List;

public class Experiment implements Runnable{
    private List<Integer> integers;
    public Experiment () {
        this.integers = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            this.integers.add(i);
        }
        for (int i = 0; i < 200; i++) {
            this.integers.add(i);
        }
    }
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 10) {
            for (Integer integer : this.integers) {
                System.out.println(integer);
            }
        }
        long currentTime = System.currentTimeMillis() - startTime;
        System.out.println("the time "+ currentTime + " is already passed");
    }
    public static void main (String[] args) {
        Thread thread1 = new Thread(new Experiment());
        Thread thread2 = new Thread(new Experiment());
        Thread thread3 = new Thread(new Experiment());
        Thread thread4 = new Thread(new Experiment());
        Thread thread5 = new Thread(new Experiment());
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
