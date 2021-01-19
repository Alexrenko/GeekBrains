package FirstSemestr.SixthLesson.Animals;

public abstract class Animal {

    private int maxRunLength;
    private int maxSwimLength;

    public Animal(int maxRunLength, int maxSwimLength) {
        this.maxRunLength =  maxRunLength;
        this.maxSwimLength = maxSwimLength;
    }

    public int getMaxRunLength() {
        return maxRunLength;
    }

    public int getMaxSwimLength() {
        return maxSwimLength;
    }

    public abstract void run(int length);

    public abstract void swim(int length);

    protected int checkLength(int length, int maxLength) {
        if (length < 0)
            return 0;
        else if (length > maxLength)
            return maxLength;
        else
            return length;
    }

}
