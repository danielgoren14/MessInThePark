import java.util.*;

public class Visitor {
    private int id;
    private String  name;
    private Map<Integer, Integer> preferences;
    private Apparatus currentApparatus;
    private int amountOfUsedApparatuses;
    private boolean isLock;
    public Visitor (int id, String name) {
        this.currentApparatus = null;
        this.amountOfUsedApparatuses = 0;
        this.id = id;
        this.name = name;
        this.preferences = initializePreferring();
        this.isLock = false;
    }

    public void setLock(boolean lock) {
        this.isLock = lock;
    }

    public boolean isLock() {
        return isLock;
    }

    private Map<Integer,Integer> initializePreferring() {
        Map<Integer,Integer> preferring = new HashMap<>();
        Random random = new Random();
        int apparatusId = 0;
        int remainingPercents = 100;
        int currentPercents;
        while (remainingPercents > 0 && apparatusId < 5) {
            currentPercents = random.nextInt(remainingPercents + 1);
//            if (remainingPercents == 0) {
//                break;
//            }
            remainingPercents -= currentPercents;
            if (remainingPercents > 0 && apparatusId == 4) {
                currentPercents += remainingPercents;
            }
            preferring.put(apparatusId,currentPercents);
            apparatusId ++;
        }
        return preferring;
    }

    public void setCurrentApparatus(Apparatus currentApparatus) {
        this.currentApparatus = currentApparatus;
    }


    public void addOneToAmountOfUsedApparatuses() {
        this.amountOfUsedApparatuses ++;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Integer> getPreferences() {
        return preferences;
    }

    public Apparatus getCurrentApparatus() {
        return currentApparatus;
    }

    public String toStringForCurrentApparatus() {
        return "Visitor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "currentApparatus " + currentApparatus + '\'' +
                ", preferences=" + preferences;

    }
    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preferences=" + preferences +
//                ", currentApparatus=" + currentApparatus +
                ", amountOfUsedApparatuses=" + amountOfUsedApparatuses +
                '}';
    }
}
