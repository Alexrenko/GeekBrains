package FirstSemestr.Java2.Lesson_1.ObstacleGTO;

import FirstSemestr.Java2.Lesson_1.Obstacle;

public abstract class ObstacleGTO implements Obstacle {
    private String name;
    private int complexity;

    public ObstacleGTO(String name, int complexity) {
        this.name = name;
        this.complexity = complexity;
    }

    public String getName() {
        return name;
    }

    public int getComplexity() {
        return complexity;
    }

}
