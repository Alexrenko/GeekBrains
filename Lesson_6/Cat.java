package FirstSemestr.SixthLesson;

public class Cat extends Animal {

    public static int catCount;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    @Override
    public void run(int length) {
        if (length < 0) length = 0;
        if (length > 200) length = 200;
        System.out.println(getName() + " пробежал " + length + " м.");
    }

    @Override
    public void swim(int length) {
        length = 0;
        System.out.println(getName() + " проплыл " + length + " м.");
    }
}
