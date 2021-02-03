package FirstSemestr.Java2.Lesson_1.ObstacleGTO;

public class GTOLabyrinth extends ObstacleGTO {
    public GTOLabyrinth() {
        super("Лабиринт", 8);
    }

    @Override
    public String getDescription() {
        return "Металлические рейки, вкопанные в землю, образующие лабиринт";
    }
}
