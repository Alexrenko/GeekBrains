package FirstSemestr.FifthLesson;

public class Main {
    public static void main(String[] args) {

        Employee[] employees = new Employee[5];
        createEployees(employees);
        printOverForty(employees);

    }

    public static void printOverForty(Employee[] employees) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() > 40)
                employees[i].print();
            System.out.println("---------------------------");
        }
    }

    public static void createEployees(Employee[] employees) {
        employees[0] = new Employee("Владимир Владимирович Путин",
                "Президент РФ",
                "putin@kremlin.info",
                "+89998764422",
                10000,
                68);

        employees[1] = new Employee("Дональд Джон Трамп",
                "Президент США",
                "donald@whitehouse.info",
                "+19998764422",
                1,
                74);

        employees[2] = new Employee("Милош Земан",
                "Президент Чехии",
                "milos@praguecastle.info",
                "+42029994422",
                12000,
                76);

        employees[3] = new Employee("Саули Нийнистё",
                "Президент Финляндии",
                "sauly@presidentialpalace.info",
                "+35829994422",
                16000,
                72);

        employees[4] = new Employee("Керсти Кальюлайд",
                "Президент Эстонии",
                "kelsty@kardiorg.info",
                "+37229994422",
                8000,
                51);
    }
}
