package FirstSemestr.Java2.Lesson_1;

public class Team {
    private String name;
    private Member[] members = new Member[4];

    //команда должна иметь название и состоять из четырех человек
    public Team(String name, Member first, Member second, Member third, Member fourth) {
        this.name = name;
        this.members[0] = first;
        this.members[1] = second;
        this.members[2] = third;
        this.members[3] = fourth;
    }

    public String getName() {
        return name;
    }

    public Member[] getMembers() {
        return members;
    }

    public void showResult() {
        System.out.printf("Результат команды \"%s\": %n", name);
        for (Member member : members) {
            System.out.printf("\t %s - %d сек. %n", member.getName(), member.getLastResult());
        }
    }

    public void printMembers() {
        System.out.printf("Состав комманды \"%s\": ", name);

        for (int i = 0; i < 4; i++) {
            System.out.print(this.members[i]);
            System.out.println(i < 3 ? ", ": ";");
        }
    }
}
