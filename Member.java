package FirstSemestr.Java2.Lesson_1;

import java.util.ArrayList;

public class Member {
    private String name;
    private int speed;
    private int lastResult;


    public Member(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return name;
    }

    public void run(ArrayList<Obstacle> obstacles) {
        float time = 0f;
        for (Obstacle obstacle : obstacles) {
            time += 1.0f * obstacle.getComplexity()/speed;
        }
        lastResult = (int) time;
    }

    public String getName() {
        return name;
    }

    public int getLastResult() {
        return lastResult;
    }
}
