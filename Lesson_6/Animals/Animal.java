package FirstSemestr.SixthLesson.Animals;

public abstract class Animal {
    private static int animalCount;
    private String name;
    protected int maxRunLength;
    protected int maxSwimLength;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public String getName() {
        return name;
    }

    public abstract void run(int length);

    public abstract void swim(int length);
}
