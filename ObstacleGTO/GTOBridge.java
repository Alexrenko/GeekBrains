package FirstSemestr.Java2.Lesson_1.ObstacleGTO;

public class GTOBridge extends ObstacleGTO {
    public GTOBridge() {
        super("Мост", 12);
    }

    @Override
    public String getDescription() {
        return "Деревянная доска, приподнятая над землей, имитирующая мост";
    }
}
