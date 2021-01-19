package FirstSemestr.SixthLesson.Animals;

public class Dog extends Animal {

    public Dog() {
        super(500, 10);
    }

    public void run(int length) {
        int distance = checkLength(length, getMaxRunLength());
        System.out.println("Пес пробежал " + distance + " м.");
    }

    public void swim(int length) {
        int distance = checkLength(length, getMaxSwimLength());
        System.out.println("Пес проплыл " + distance + " м.");
    }
}
