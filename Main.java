package FirstSemestr.Java2.Lesson_1;

import FirstSemestr.Java2.Lesson_1.ObstacleDevil.DevilBridge;
import FirstSemestr.Java2.Lesson_1.ObstacleGTO.GTOBridge;
import FirstSemestr.Java2.Lesson_1.ObstacleGTO.GTOLabyrinth;
import FirstSemestr.Java2.Lesson_1.ObstacleGTO.GTOWall;

public class Main {
    public static void main(String[] args) {

        Course course = buildCourse();

        Team teamBeavers = recruitTeam();

        course.getDescription();

        course.dolt(teamBeavers);

        teamBeavers.showResult();


    }

    private static Course buildCourse() {
        Course c = new Course();
        c.addObstacle(new GTOLabyrinth());
        c.addObstacle(new GTOWall());
        c.addObstacle(new GTOBridge());
        c.addObstacle(new DevilBridge());
        return c;
    }

    private static Team recruitTeam() {
        Team team = new Team("Летучие бобры",
                        new Member("Антон", 5),
                        new Member("Андрей", 4),
                        new Member("Ольга", 6),
                        new Member("Шумахер911", 11));
        return team;
    }
}
