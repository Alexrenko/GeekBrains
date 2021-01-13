package FirstSemestr.FifthLesson;

public class Employee {
    private String FIO;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    public Employee(String FIO, String position, String email, String phoneNumber, int salary, int age) {
        this.FIO = FIO;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return  "Ф.И.О: " + FIO + '\n' +
                "Должность: " + position + '\n' +
                "e-mail: " + email + '\n' +
                "Телефон: " + phoneNumber + '\n' +
                "Зарплата: " + salary + "$" + '\n' +
                "Возраст: " + age;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
