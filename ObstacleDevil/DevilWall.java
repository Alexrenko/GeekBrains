package FirstSemestr.Java2.Lesson_1.ObstacleDevil;

public class DevilWall extends ObstacleDevil {
    public DevilWall() {
        super("Дьявольска стена", 41);
    }

    @Override
    public String getDescription() {
        return "Стена высотой 4 метра, перед которой расположен глубокий ров";
    }
}
