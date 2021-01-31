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

}
