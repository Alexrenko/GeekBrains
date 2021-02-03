package FirstSemestr.Java2.Lesson_1.ObstacleGTO;

public class GTOWall extends ObstacleGTO {
    public GTOWall() {
        super("Стена", 9);
    }

    @Override
    public String getDescription() {
        return "Деревянная стена высотой два метра";
    }
}
