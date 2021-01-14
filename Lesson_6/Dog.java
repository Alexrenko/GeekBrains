package FirstSemestr.SixthLesson;

public class Dog extends Animal {
    public static int dogCount;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

    @Override
    public void run(int length) {
        if (length < 0) length = 0;
        if (length > 500) length = 500;
        System.out.println(getName() + " пробежал " + length + " м.");
    }

    @Override
    public void swim(int length) {
        if (length < 0) length = 0;
        if (length > 10) length = 10;
        System.out.println(getName() + " проплыл " + length + " м.");
    }
}
