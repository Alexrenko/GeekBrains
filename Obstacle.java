package FirstSemestr.Java2.Lesson_1;

public class Obstacle {

    private String name;
    private int complexity;

    public Obstacle(String name, int complexity) {
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
