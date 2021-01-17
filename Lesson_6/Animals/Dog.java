package FirstSemestr.SixthLesson.Animals;

public class Dog extends Animal {

    private static int dogCount;

    public Dog(String name) {
        super(name);
        maxRunLength = 500;
        maxSwimLength = 10;
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

    public static void setDogCount(int dogCount) {
        Dog.dogCount = dogCount;
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
