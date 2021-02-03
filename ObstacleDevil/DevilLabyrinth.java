package FirstSemestr.Java2.Lesson_1.ObstacleDevil;

public class DevilLabyrinth extends ObstacleDevil {

    public DevilLabyrinth() {
        super("Дьявольский лабиринт", 34);
    }

    @Override
    public String getDescription() {
        return "Лабиринт из металлических реек, покрытый острыми шипами";
    }
}
