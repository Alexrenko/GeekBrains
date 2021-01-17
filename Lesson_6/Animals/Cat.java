package FirstSemestr.SixthLesson.Animals;

public class Cat extends Animal {

    private static int catCount;

    public Cat(String name) {
        super(name);
        maxRunLength = 200;
        maxSwimLength = 0;
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    public static void setCatCount(int catCount) {
        Cat.catCount = catCount;
    }

    @Override
    public void run(int length) {
        int distance = checkLength(length, maxRunLength);
        System.out.println(getName() + " пробежал " + distance + " м.");
    }

    @Override
    public void swim(int length) {
        int distance = checkLength(length, maxSwimLength);
        System.out.println(getName() + " проплыл " + distance + " м.");
    }

    private int checkLength(int length, int maxLength) {
        if (length < 0)
            return 0;
        else if (length > maxLength)
            return maxLength;
        else
            return length;
    }
}
