package FirstSemestr.Java2.Lesson_1.ObstacleDevil;

import FirstSemestr.Java2.Lesson_1.Obstacle;

public abstract class ObstacleDevil implements Obstacle {

    private String name;
    private int complexity;

    public ObstacleDevil(String name, int complexity) {
        this.name = name;
        this.complexity = complexity;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getComplexity() {
        return complexity;
    }

}
