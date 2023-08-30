import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Apparatus implements Runnable{
    private static List<Visitor> existingVisitors;
    private List<Visitor> visitors;
    private int id;
    private String name;
    private int activityTime;
    private int capacity;
    private int currentVisitorsAmount;
    private int startVisitors;

    public Apparatus (int startVisitors, List<Visitor> visitorList,int id, String name) {
        existingVisitors = visitorList;
        this.visitors = new ArrayList<>();
        this.id = id;
        this.name = name;
        Random random = new Random();
        this.activityTime = random.nextInt(10,31);
        this.capacity = random.nextInt(1,7);
        this.currentVisitorsAmount = 0;
        this.startVisitors = startVisitors;
    }
    public Apparatus (int startVisitors, int id, String name) {
        this.visitors = new ArrayList<>();
        this.id = id;
        this.name = name;
        Random random = new Random();
        this.activityTime = random.nextInt(10,31);
        this.capacity = random.nextInt(1,7);
        this.startVisitors = startVisitors;
    }

    @Override
    public void run() {
//        long startTime = System.currentTimeMillis();
//        System.out.println(startTime);
//        while (System.currentTimeMillis() - startTime < 1000 * 10){

        for (int i = this.startVisitors; i < this.startVisitors + 10; i++) {
            Visitor visitor = new Visitor(i,"Visitor_"+ i);
////        }
//            for (Visitor visitor : existingVisitors) {
                System.out.println(this.toString());
                synchronized (visitor) {
                    if (!visitor.isLock()) {
                        int apparatusId = this.getId();
                        System.out.println(visitor.getId() + ") - random "+apparatusId);
                        boolean isPreferenceRight = false;
                        if (visitor.getPreferences().get(apparatusId) != null && visitor.getPreferences().get(apparatusId) != 0) {
                            isPreferenceRight = true;
                            System.out.println("preference of visitor: " + visitor + "\n" + "and the percentages of the preference = " + visitor.getPreferences().get(apparatusId) + " accepted");
                        }
                        if (isPreferenceRight) {
                                if (this.getCurrentVisitorsAmount() < this.getCapacity()) {
                                    visitor.setLock(true);
                                    visitor.setCurrentApparatus(this);
                                    System.out.println("visitor to add = " + visitor.toString());
                                    this.addVisitor(visitor);
                                    this.addOneToCurrentVisitorsAmount();
                                    visitor.addOneToAmountOfUsedApparatuses();
                                    this.getVisitors().stream().forEach(visitor1 -> {
                                        System.out.println(visitor1.getId() + ")\n" + visitor1.toString());
                                    });
                                } else {
                                    System.out.println("the current apparatus " + this.toString() + " is already full in capacity and waiting starting a round");
                                    int waitingTime = this.activityTime * 1000;
                                    System.out.println("waiting time " + waitingTime);
                                    try {
                                        Thread.sleep(waitingTime);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("waiting time " + waitingTime + " is already over");
                                    this.initialize(visitor);
                                }
                        } else {
                            System.out.println(visitor.getId() + ") we need to ignore from you" );
                        }
                    } else {
                        System.out.println(visitor + " is already belongs to other apparatus");
                    }
                }
            }
        System.out.println("time is over");
//        }
//        long currentTime = System.currentTimeMillis() - startTime;
//        System.out.println("the time "+ currentTime + " is already passed");
//        System.out.println("time is finish");
    }

    public int getId() {
        return id;
    }

    public int getActivityTime() {
        return activityTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addOneToCurrentVisitorsAmount() {
        this.currentVisitorsAmount += 1;
    }

    public int getCurrentVisitorsAmount() {
        return currentVisitorsAmount;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void initialize (Visitor visitor) {
        this.visitors = new ArrayList<>();
        this.currentVisitorsAmount = 0;
        visitor.setLock(false);
    }

    public void addVisitor(Visitor visitor) {
        this.visitors.add(visitor);
    }

    @Override
    public String toString() {
        return "Apparatus{" +
                " id=" + id +
                ", name='" + name + '\'' +
                ", activityTime=" + activityTime +
                ", capacity=" + capacity +
                ", currentVisitorsAmount=" + currentVisitorsAmount +
                ", visitors=" + visitors +
                '}';
    }


    public static void main(String[] args) {
//        List<Apparatus> apparatusList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Apparatus apparatus = new Apparatus(i,"Apparatus_" + i);
//            apparatusList.add(apparatus);
//        }
        List<Visitor> visitorList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Visitor visitor = new Visitor(i,"Visitor_" + i);
            visitorList.add(visitor);
        }

        Thread apparatus1 = new Thread(new Apparatus(0,visitorList,1,"Apparatus_1"));
        Thread apparatus2 = new Thread(new Apparatus(9,2,"Apparatus_2"));
        Thread apparatus3 = new Thread(new Apparatus(19,3,"Apparatus_3"));
        Thread apparatus4 = new Thread(new Apparatus(29,4,"Apparatus_4"));
        Thread apparatus5 = new Thread(new Apparatus(39,5,"Apparatus_5"));

        apparatus1.start();
        apparatus2.start();
        apparatus3.start();
        apparatus4.start();
        apparatus5.start();


//        System.out.println("Hello world!");
    }
}
