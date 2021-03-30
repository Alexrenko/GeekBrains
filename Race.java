package FirstSemestr.Java3.Lesson_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() {
        return stages;
    }
    public CyclicBarrier barrierStart;
    public CountDownLatch finish;
    public Race(int cars_count, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        barrierStart = new CyclicBarrier(cars_count);
        finish = new CountDownLatch(cars_count);
    }
}