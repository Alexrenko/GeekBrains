package FirstSemestr.Java2.Lesson_1.ObstacleDevil;

public class DevilBridge extends ObstacleDevil {

    public DevilBridge() {
        super("Дьявольский мост", 54);
    }

    @Override
    public String getDescription() {
        return "Набор стоящих неподалеку деревянных реек, разной длины, переброшенных через водоем с акулами";
    }
}
