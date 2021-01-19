package FirstSemestr.SixthLesson.Animals;

public class Cat extends Animal {

    public Cat() {
        super(200, 0);
    }

    public void run(int length) {
        int distance = checkLength(length, getMaxRunLength());
        System.out.println("Кот пробежал " + distance + " м.");
    }

    public void swim(int length) {
        int distance = checkLength(length, getMaxSwimLength());
        System.out.println("Кот проплыл " + distance + " м.");
    }
}
