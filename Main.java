package FirstSemestr.Java2.Lesson_1;

public class Main {
    public static void main(String[] args) {

        Course course = buildCourse();

        Team teamBeavers = recruitTeam();

        course.dolt(teamBeavers);

        teamBeavers.showResult();
    }

    private static Course buildCourse() {
        Course c = new Course();
        c.addObstacle(new Obstacle("Лабиринт", 8));
        c.addObstacle(new Obstacle("Стена", 9));
        c.addObstacle(new Obstacle("Развушенный мост", 12));
        c.addObstacle(new Obstacle("Вал", 14));
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
