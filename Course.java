package FirstSemestr.Java2.Lesson_1;

import java.util.ArrayList;

public class Course {
    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

    public void dolt(Team team) {
        for(Member member : team.getMembers()) {
            member.run(obstacles);
        }
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public void getDescription() {
        System.out.println("Описание препятствий:");
        for (int i = 0; i < obstacles.size(); i++) {
            System.out.printf("\t препятствие %d: ", i + 1);
            System.out.println(obstacles.get(i).getDescription());
        }
        System.out.println();
    }
}
